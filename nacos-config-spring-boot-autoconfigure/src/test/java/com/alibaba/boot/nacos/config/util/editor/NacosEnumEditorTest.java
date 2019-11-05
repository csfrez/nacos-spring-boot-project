package com.alibaba.boot.nacos.config.util.editor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NacosEnumEditorTest {
	
	public enum King{
	    ELVIS,
	    MAKER
	}

	public static void main(String[] args) {
		NacosEnumEditor nee = new NacosEnumEditor(King.class);
		nee.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("evt.getOldValue()=" + evt.getOldValue());
				System.out.println("evt.getNewValue()=" + evt.getNewValue());
			}
		});
		nee.setAsText("elvis");
		System.out.println(nee.getValue());
		System.out.println(nee.getJavaInitializationString());
	}

}
