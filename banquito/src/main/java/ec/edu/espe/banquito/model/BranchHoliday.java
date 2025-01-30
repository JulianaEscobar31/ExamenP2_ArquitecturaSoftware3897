package ec.edu.espe.banquito.model;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchHoliday {

    private Date date;
    private String name;

}
