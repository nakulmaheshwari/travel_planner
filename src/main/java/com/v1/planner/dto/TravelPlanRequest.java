import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;

@Getter
@Setter
public class TravelPlanRequest {
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(columnDefinition = "TEXT")
    private String description;

    
    
    
}