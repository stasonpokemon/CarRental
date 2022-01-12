package pojo;


import java.util.Objects;

/**
 * Класс сущности со свойством <b>id</b>.
 *
 * @version 1.1
 * @autor Станислав Требников
 */
public abstract class Entity {

    /**
     * Поле идентификатора сущности
     */
    private Integer id;

    /**
     * Функция получения идентификатора сущности {@link Entity#id}
     *
     * @return возвращает идентификатор сущности
     */
    public Integer getId() {
        return id;
    }

    /**
     * Процедура определения идентификатора сущности {@link Entity#id}
     *
     * @param id - идентификатор сущности
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Переопределённый метод equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    /**
     * Переопределённый метод hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
