package ec.edu.espe.banquito.controller.dto;

import java.sql.Date;
import java.util.List;


import ec.edu.espe.banquito.model.BranchHoliday;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchDTO {

    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String state;
    private Date creationDate;
    private Date lastModifiedDate;
    private List <BranchHoliday> branchHolidays;
}
