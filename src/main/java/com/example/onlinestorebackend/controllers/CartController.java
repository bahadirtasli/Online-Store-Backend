package com.example.onlinestorebackend.controllers;

import com.example.onlinestorebackend.exceptions.CartNotFoundException;
import com.example.onlinestorebackend.exceptions.OrderLineNotFoundException;
import com.example.onlinestorebackend.exceptions.ProductNotFoundException;
import com.example.onlinestorebackend.models.Cart;
import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;
import com.example.onlinestorebackend.services.CartService;
import com.example.onlinestorebackend.services.OrderLineService;
import com.example.onlinestorebackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Bahadir Tasli
 * @Date 3/29/2023
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public String showCartPage(Model model, @ModelAttribute("message") String message,
                                    @ModelAttribute("messageType") String messageType) {
        model.addAttribute("carts", cartService.findAllCarts());
        return "cart/list-cart";
    }

    @GetMapping("/{id}")
    public String showCartViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("cart", cartService.findCartById(id));
            return "cart/view-cart";
        } catch (CartNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.deleteCartById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Cart %d deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/cart";
        } catch (CartNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreCart(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.restoreCartById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Cart %d deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/cart";
        } catch (CartNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    // To show create product form page
    @GetMapping("/create")
    public String createCart(Model model, @ModelAttribute("cart")Cart cart,@ModelAttribute("product") Product product,
                                  @ModelAttribute("message") String message,
                                  @ModelAttribute("messageType") String messageType) {
        model.addAttribute("products",productService.findAllProducts());
        return "cart/create-cart";
    }

    // Called shen we press submit button in the create product form
    @PostMapping("/create")
    public String createCart(Cart cart, RedirectAttributes redirectAttributes) {
        try {
            Cart searchCart = cartService.findCartById(cart.getId());
            redirectAttributes.addFlashAttribute("message", String.format("Cart %d already exists!", cart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/cart/create-cart";
        } catch (CartNotFoundException e) {
            cartService.createCart(cart);
            redirectAttributes.addFlashAttribute("message", String.format("Product %d has been created successfully!", cart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/cart";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateCartPage(@PathVariable Long id,
                                        RedirectAttributes redirectAttributes,
                                        @RequestParam(value = "cart", required = false) Cart cart,
                                        Model model) {
        if (cart == null) {
            try {
                model.addAttribute("cart", cartService.findCartById(id));
            } catch (CartNotFoundException e) {
                return handleException(redirectAttributes, e);
            }
        }
        return "cart/update-cart";
    }

    @PostMapping("/update")
    public String updateCart(Cart cart, RedirectAttributes redirectAttributes) {
        try {
            cartService.updateCart(cart);
            redirectAttributes.addFlashAttribute("message", String.format("Product(%s) has been created successfully!", cart.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/cart";
        } catch (CartNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/create-by-orderLine/{orderLineId}")
    public String createOrderLineByProduct(@PathVariable Long orderLineId, RedirectAttributes redirectAttributes) {
        try {
            OrderLine orderLine = orderLineService.findOrderLineById(orderLineId);
            cartService.createCartByOrderLine(orderLine);

            redirectAttributes.addFlashAttribute("message", "Product added to the cart!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/orderLine";
        } catch (OrderLineNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }


    // PRIVATE METHODS //
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/cart";
    }
}
