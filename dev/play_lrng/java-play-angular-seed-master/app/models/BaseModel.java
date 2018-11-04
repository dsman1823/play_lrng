package models;

import io.ebean.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public class BaseModel extends Model {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected Long id;

   public Long getId() {
      return id;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      BaseModel baseModel = (BaseModel) o;
      return Objects.equals(id, baseModel.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }

   public void setId(Long id) {
      this.id = id;


   }
}
