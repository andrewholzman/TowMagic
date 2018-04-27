package com.towmagic.ui;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.towmagic.dto.Customer;

@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String custId) {
		
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{towAddVO}", TowAddVO.class);;
		TowAddVO towAddVO = (TowAddVO)vex.getValue(ctx.getELContext());

		return towAddVO.getCustomer(custId);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object cust) {
		if ((cust == null)||(cust=="")) {
			return "null";
		}
		Customer customer = (Customer)cust;
		return customer.toString();
	}

}
