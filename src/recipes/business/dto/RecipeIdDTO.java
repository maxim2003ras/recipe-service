package recipes.business.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maksim Rasporski
 * @link https://vk.com/maximrasporsky
 */
@Data
@NoArgsConstructor
public class RecipeIdDTO {
    private Long id;

    public RecipeIdDTO(final Long id) {
        this.id = id;
    }
}
