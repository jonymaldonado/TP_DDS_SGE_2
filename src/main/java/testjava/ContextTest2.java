package testjava;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import ar.com.sge.util.ModelHelper;


public class ContextTest2 {
	private ModelHelper model;
	
	@Test
	public void init() {
		this.model = new ModelHelper();
	}
	
	
}