package controllers;

import models.Product;

public class ProductLine {
    private Product _prod;
    private int _quant;

    public ProductLine(Product prod, int quant) {
        this._prod = prod;
        this._quant = quant;
    }

    public Product getProd() {
        return this._prod;
    }

    public void setProd(Product prod) {
        this._prod = prod;
    }

    public int getQuant() {
        return this._quant;
    }

    public void setQuant(int quant) {
        this._quant = quant;
    }

    @Override
    public String toString() {
        return this._prod.toString() + " - " +  _quant;
    }

}
