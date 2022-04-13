package info.schuwan.api1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reimb")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    ReimbType type;

    @Column(name = "amount")
    Float amount;

    @Enumerated(EnumType.STRING)
    ReimbStatus status;

    /**
     * this methods does taht
     */
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "emp_user_id")
    @JsonBackReference
    private ReimbsUser employee;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "man_user_id")
    @JsonBackReference
    private ReimbsUser manager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Reimbursement that = (Reimbursement) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setType(ReimbType type) {
        this.type = type;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setStatus(ReimbStatus status) {
        this.status = status;
    }

    public void setEmployee(ReimbsUser employee) {
        this.employee = employee;
    }

    public void setManager(ReimbsUser manager) {
        this.manager = manager;
    }
}
