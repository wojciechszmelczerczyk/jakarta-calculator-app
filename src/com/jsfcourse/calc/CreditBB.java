package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CreditBB {
	private Double value; // change to Integer for validation
	private Integer years;
	private Double percent; // change to Double for validataion
	private Integer months;
	private Double q;
	private Double result;

	@Inject
	FacesContext ctx;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}
	
	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
	

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}


	public boolean doTheMath() {
		try {
			
			months = 12;
			q = 1 + (percent/months);
			
			result = ((value * Math.pow(q,years))*((q-1)) / (Math.pow(q,years)-1));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}
	
	
	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult"; 
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
