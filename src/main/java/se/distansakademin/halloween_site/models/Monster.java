package se.distansakademin.halloween_site.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String monsterId;

    @NonNull
    private String monsterName;

    public Monster(@NonNull String monsterName) {
        this.monsterName = monsterName;
    }
}
