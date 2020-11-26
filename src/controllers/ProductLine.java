package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import models.Product;

public class ProductLine {
    private Product _prod;
    private IntegerProperty _quant = new SimpleIntegerProperty();
    private IntegerProperty _maxquant = new SimpleIntegerProperty();

    public ProductLine(Product prod, int quant) {
        this._prod = prod;
        this._quant.setValue(quant);
        this._maxquant.setValue(20);
    }

    public Product getProd() {
        return this._prod;
    }

    public void setProd(Product prod) {
        this._prod = prod;
    }

    public int getQuant() {
        return this._quant.getValue();
    }

    public IntegerProperty getQuantProperty() {
        return this._quant;
    }

    public IntegerProperty getMaxQuantProperty() {
        return this._maxquant;
    }

    public void setQuant(int quant) {
        this._quant.setValue(quant);
    }

    public float getTotal() {
        return this._quant.getValue() * this._prod.getTarif();
    }

    @Override
    public String toString() {
        return this._prod.toString() + " - " + _quant;
    }

}
