package com.ui.ac.shop.ir.shop.controller;

import controller.Exeptions.ObjectDoesNotExist;
import model.Product.BaseProduct;
import model.comment.Comment;

import java.util.ArrayList;

public class CommentController {
    private static final ArrayList<Comment> comments;

    static {
        comments = new ArrayList<>();
    }

    public static ArrayList<Comment> getComments() {
        return comments;
    }


    public static Comment addComment(String text) throws ObjectDoesNotExist {
        Comment comment = new Comment(CustomerController.getCurrentCustomer(), ((BaseProduct) ProductController.getCurrentProduct()).getId()
                , text, CustomerController.isBuyer((BaseProduct) ProductController.getCurrentProduct()));
        comments.add(comment);
        return comment;
    }

}
