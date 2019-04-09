package springprojdect.stunodemo.model.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMsg {

    private String msg;
    private int status;
    private LocalDateTime time;
}
