package ir.maktab.data.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Manager {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String password;

    public Manager() {

    }

    public static final class ManagerBuilder {
        private String userName;
        private String password;

        private ManagerBuilder() {
        }

        public static ManagerBuilder aManager() {
            return new ManagerBuilder();
        }

        public ManagerBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public ManagerBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Manager build() {
            Manager manager = new Manager();
            manager.setUserName(userName);
            manager.setPassword(password);
            return manager;
        }
    }
}
