package ru.ilya.NauJava.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.ilya.NauJava.model.Note;
import ru.ilya.NauJava.model.Report;
import ru.ilya.NauJava.model.ReportStatus;
import ru.ilya.NauJava.repository.NoteRepository;
import ru.ilya.NauJava.repository.ReportRepository;
import ru.ilya.NauJava.repository.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    private final AtomicLong userCount;
    private final AtomicReference<List<Note>> notes;


    public ReportService(ReportRepository reportRepository, UserRepository userRepository,
                         NoteRepository noteRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.userCount = new AtomicLong(0);
        this.notes = new AtomicReference<>();
    }

    public String getInfoById(Long id) {
        Report report = reportRepository.findById(id).orElse(null);
        if (report == null) {
            return null;
        }
        switch (report.getStatus()) {
            case CREATED -> {
                return "Created";
            }
            case COMPLETED -> {
                return report.getInfo();
            }
            default -> {
                return "Error";
            }
        }

    }

    @Transactional
    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        Report savedReport = reportRepository.save(report);

        return savedReport.getId();
    }

    @Transactional
    public void proceedReport(Long id) {
        CompletableFuture.supplyAsync(() -> {
            Report report = reportRepository.findById(id).orElse(null);
            long startTime = System.currentTimeMillis();
            if (report == null) {
                return null;
            }
            AtomicLong userCountElapsedTime = new AtomicLong();
            AtomicLong noteListElapsedTime = new AtomicLong();
            Thread userCountThread = new Thread(() -> {
                long userCountTime = System.currentTimeMillis();
                userCount.set(userRepository.count());
                userCountElapsedTime.set(System.currentTimeMillis() - userCountTime);
            });

            Thread noteListThread = new Thread(() -> {
                long noteListTime = System.currentTimeMillis();
                notes.set((List<Note>) noteRepository.findAll());
                noteListElapsedTime.set(System.currentTimeMillis() - noteListTime);
            });

            userCountThread.start();

            noteListThread.start();

            try {

                userCountThread.join();
                noteListThread.join();

                long totalElapsedTime = System.currentTimeMillis() - startTime;
                String reportInfo = buildReportInfo(totalElapsedTime, userCountElapsedTime.get(),
                        noteListElapsedTime.get());
                report.setInfo(reportInfo);
                report.setStatus(ReportStatus.COMPLETED);
                reportRepository.save(report);
                return reportInfo;
            } catch (InterruptedException e) {
                report.setStatus(ReportStatus.ERROR);
                report.setInfo(e.getMessage());
                reportRepository.save(report);
                return null;
            }
        });

    }

    private String buildReportInfo(long totalTime, long userCountTime, long noteListTime) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div> Total time elapsed: ").append(totalTime).append(" ms").append("</div>\n");
        sb.append("<div> User count time elapsed: ").append(userCountTime).append(" ms</div>\n");
        sb.append("<div> User count: ").append(userCount.get()).append("</div>\n");
        sb.append("<div> Note list get elapsed: ").append(noteListTime).append(" ms</div>\n");
        sb.append("<table>");
        sb.append("""
                <tr>
                        <th>ID</th>
                        <th>Header</th>
                        <th>Content</th>
                        <th>Creation date</th>
                        <th></th>
                    </tr>""");
        for (Note note : notes.get()) {
            sb.append("<tr>");
            sb.append("<td>").append(note.getId()).append("</td>");
            sb.append("<td>").append(note.getHeader()).append("</td>");
            sb.append("<td>").append(note.getContent()).append("</td>");
            sb.append("<td>").append(note.getCreation_date()).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
