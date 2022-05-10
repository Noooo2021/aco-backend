package tacos.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
public class TacoDesignController {

	private final IngredientRepository ingredientRepo;
	private final TacoRepository tacoRepo;

	@Autowired
	public TacoDesignController(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = tacoRepo;
	}

	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		List<Ingredient> ingredients = (List<Ingredient>) ingredientRepo.findAll();
		Type[] types = Ingredient.Type.values();
		// thêm attribute các loại
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}

	}

	@GetMapping
	public String showDesignForm(Model model) {
		model.addAttribute("taco", new Taco());
		return "design";
	}

	// lọc các thành phần theo loại
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		List<Ingredient> ingrList = new ArrayList<Ingredient>();
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType().equals(type))
				ingrList.add(ingredient);
		}
		return ingrList;
	}

	@PostMapping
	public String processDesign(Taco taco) {
//		tacoRepo.save(taco);
		System.out.println("sucess!");
		return "redirect:/orders/current";
	}
}