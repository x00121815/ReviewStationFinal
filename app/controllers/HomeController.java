package controllers;

import play.api.Environment;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;
import models.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

	private FormFactory formFactory;
	@Inject
	public HomeController(FormFactory f) {
		this.formFactory = f;
	}


    public Result index() {
        return ok(index.render());
    }

    public Result games() {
        return ok(games.render());
    }

    public Result gta() {
        return ok(gta.render());
    }

    public Result battlefield() {
        return ok(battlefield.render());
    }

    public Result witcher() {
        return ok(witcher.render());
    }

    public Result watchdogs() {
        return ok(watchdogs.render());
    }

    public Result purchase() {
     	return ok(purchase.render());
    }

    public Result products() {
	List<Product> productsList = Product.findAll();
	return ok(products.render(productsList));
    }

    public Result addProduct() {
	Form<Product> addProductForm = formFactory.form(Product.class);
	return ok(addProduct.render(addProductForm));
    }

    public Result addProductSubmit() {
	 Form<Product> newProductForm = formFactory.form(Product.class).bindFromRequest();

        if(newProductForm.hasErrors()) {
            return badRequest(addProduct.render(newProductForm));
        }
	
	Product p = newProductForm.get();
	if (p.getId() == null) {
		p.save();
	} else if (p.getId() != null) {
		p.update();
	}

        Product newProduct = newProductForm.get();

        newProduct.save();

        flash("success", "Product " + newProduct.getName() + " has been created/updated");

        return redirect(routes.HomeController.products());

    }

	public Result deleteProduct(Long id) {
		Product.find.ref(id).delete();
		flash("success","Product has been deleted");
		return redirect(routes.HomeController.products());
	}
	
	@Transactional	
	public Result updateProduct(Long id) {
		Product p;
		Form<Product> productForm;

		try {
			p = Product.find.byId(id);
			productForm = formFactory.form(Product.class).fill(p);
		} catch (Exception ex) {
			return badRequest("Error");
			}
		return ok(addProduct.render(productForm));	
	}
}
