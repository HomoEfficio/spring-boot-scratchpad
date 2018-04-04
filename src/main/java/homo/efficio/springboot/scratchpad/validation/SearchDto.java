package homo.efficio.springboot.scratchpad.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author homo.efficio@gmail.com
 * created on 2018-04-04
 */
@Getter
@Setter
@LimitSearchPeriod(message = "조회 기간은 90일 이내여야 합니다.")
public class SearchDto implements ValidPeriod {

    @NotNull(message = "keyword가 명시되어야 합니다.")
    private String keyword;

    @NotNull(message = "기간 시작 일자가 명시되어야 합니다.")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate;

    @NotNull(message = "기간 종료 일자가 명시되어야 합니다.")
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate endDate;

    public SearchDto() {
    }

    public SearchDto(String keyword, LocalDate startDate, LocalDate endDate) {
        this.keyword = keyword;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean isValidPeriod() {
        return endDate.isBefore(startDate.plusMonths(3))
                && (endDate.isEqual(startDate) || endDate.isAfter(startDate));
    }

    @Override
    public String toString() {
        return "SearchDto{" +
                "keyword='" + keyword + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
