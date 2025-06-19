package in.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book{

    @Id
    private Integer bookid;
    private String bookname;
    private Double bookprice;

}
