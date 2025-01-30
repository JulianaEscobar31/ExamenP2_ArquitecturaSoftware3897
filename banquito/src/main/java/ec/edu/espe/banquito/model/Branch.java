package ec.edu.espe.banquito.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Branch {

    @Id
    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String state;
    private Date creationDate;
    private Date lastModifiedDate;
    private List <BranchHoliday> branchHolidays;

}
