package com.techfly.demo.bean;

public class DemoBean {

	private String tv1;
	private String tv2;
	private String tv3;
	private String tv4;
	private String tv5;

	private String tv6;

	private String[] picArray;

	private String tv7;
	private String tv8;
	private String tv9;
	private String tv10;

	public DemoBean(String tv1, String tv2) {
		this.tv1 = tv1;
		this.tv2 = tv2;
	}

	public DemoBean(String tv1, String tv2, String tv3, String tv4, String tv5) {
		this.tv1 = tv1;
		this.tv2 = tv2;
		this.tv3 = tv3;
		this.tv4 = tv4;
		this.tv5 = tv5;
	}

	public DemoBean(String tv1, String tv2, String tv3, String tv4, String tv5, String[] array) {
		this.tv1 = tv1;
		this.tv2 = tv2;
		this.tv3 = tv3;
		this.tv4 = tv4;
		this.tv5 = tv5;
		this.picArray = array;
	}

	public DemoBean(String tv1, String tv2, String tv3, String tv4, String tv5, String tv6, String tv7, String tv8, String tv9, String tv10) {
		this.tv1 = tv1;
		this.tv2 = tv2;
		this.tv3 = tv3;
		this.tv4 = tv4;
		this.tv5 = tv5;
		this.tv6 = tv6;
		this.tv7 = tv7;
		this.tv8 = tv8;
		this.tv9 = tv9;
		this.tv10 = tv10;
	}

	public String getTv1() {
		return tv1;
	}

	public void setTv1(String tv1) {
		this.tv1 = tv1;
	}

	public String getTv2() {
		return tv2;
	}

	public void setTv2(String tv2) {
		this.tv2 = tv2;
	}

	public String getTv3() {
		return tv3;
	}

	public void setTv3(String tv3) {
		this.tv3 = tv3;
	}

	public String getTv4() {
		return tv4;
	}

	public void setTv4(String tv4) {
		this.tv4 = tv4;
	}

	public String getTv5() {
		return tv5;
	}

	public void setTv5(String tv5) {
		this.tv5 = tv5;
	}

	public String getTv6() {
		return tv6;
	}

	public void setTv6(String tv6) {
		this.tv6 = tv6;
	}

	public String getTv7() {
		return tv7;
	}

	public void setTv7(String tv7) {
		this.tv7 = tv7;
	}

	public String getTv8() {
		return tv8;
	}

	public void setTv8(String tv8) {
		this.tv8 = tv8;
	}

	public String getTv9() {
		return tv9;
	}

	public void setTv9(String tv9) {
		this.tv9 = tv9;
	}

	public String getTv10() {
		return tv10;
	}

	public void setTv10(String tv10) {
		this.tv10 = tv10;
	}

	public String[] getPicArray() {
		return picArray;
	}

	public void setPicArray(String[] picArray) {
		this.picArray = picArray;
	}
}
