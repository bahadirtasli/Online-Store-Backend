package com.example.onlinestorebackend.controllers;

import com.example.onlinestorebackend.exceptions.OrderLineNotFoundException;
import com.example.onlinestorebackend.exceptions.ProductNotFoundException;
import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;
import com.example.onlinestorebackend.services.OrderLineService;
import com.example.onlinestorebackend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Bahadir Tasli
 * @Date 4/3/2023
 */
@Controller
@RequestMapping("/orderLine")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public String showOrderLinePage(Model model, @ModelAttribute("message") String message,
                                    @ModelAttribute("messageType") String messageType) {
        model.addAttribute("orderlines", orderLineService.findAllOrderLines());
        return "orderLine/list-orderline";
    }

    @GetMapping("/{id}")
    public String showOrderLineViewPage(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("orderline", orderLineService.findOrderLineById(id));
            return "orderLine/view-orderline";
        } catch (OrderLineNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderLine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            orderLineService.deleteOrderLineById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Orderline %d deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/orderLine";
        } catch (OrderLineNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreOrderLine(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            orderLineService.restoreOrderLineById(id);
            redirectAttributes.addFlashAttribute("message", String.format("Orderline %d deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/orderline";
        } catch (OrderLineNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }


    @GetMapping("/create-by-product/{productId}")
    public String createOrderLineByProduct(@PathVariable Long productId, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.findProductById(productId);
            orderLineService.createOrderLineByProduct(product);
            redirectAttributes.addFlashAttribute("message", "Product added to the cart!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/product";
        } catch (ProductNotFoundException e) {
            return handleException(redirectAttributes, e);
        }
    }


    // To show create product form page
    @GetMapping("/create")
    public String createOrderLine(Model model, @ModelAttribute("orderLine") OrderLine orderLine, @ModelAttribute("product") Product product,
                                  @ModelAttribute("message") String message,
                                  @ModelAttribute("messageType") String messageType) {
        model.addAttribute("products", productService.findAllProducts());
        return "orderLine/create-orderLine";
    }

    // Called when we press submit button in the create product form
    @PostMapping
    public String createOrderLine(OrderLine orderLine, RedirectAttributes redirectAttributes) {
        try {
            OrderLine searchOrderLine = orderLineService.findOrderLineById(orderLine.getId());
            redirectAttributes.addFlashAttribute("message", String.format("Product %d already exists!", orderLine.getId()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/orderLine/create-orderline";
        } catch (OrderLineNotFoundException e) {
            orderLineService.createOrderLine(orderLine);
            redirectAttributes.addFlashAttribute("message", String.format("Product %d has been created successfully!", orderLine.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/orderLine";
        }
    }




    // PRIVATE METHODS //
    private String handleException(RedirectAttributes redirectAttributes, Exception e) {
        redirectAttributes.addFlashAttribute("message", e.getLocalizedMessage());
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/orderLine";
    }
}
