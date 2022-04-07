package recipes.business.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    @NotBlank
    private String email;

    @Size(min = 8)
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Recipe> allRecipesForUser = new ArrayList<>();

}
