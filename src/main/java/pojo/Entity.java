package pojo;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public abstract class Entity {

// Expose - аннотация, которая позволяет игнорировать или не игнорировать поле при сериализации
    @Expose(serialize = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
