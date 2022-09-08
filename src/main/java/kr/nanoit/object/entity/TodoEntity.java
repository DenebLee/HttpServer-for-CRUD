package kr.nanoit.object.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    private long todoId;
    private String createdAt;
    private String deletedAt;
    private String content;
}
