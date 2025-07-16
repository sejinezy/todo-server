package org.example.web.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaskRequest {

    private String title;
    private String description;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}
