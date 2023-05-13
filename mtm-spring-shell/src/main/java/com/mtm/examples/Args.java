//package com.mtm.examples;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.constraints.Min;
//
//public class Args {
//	@Parameter
//	private List<String> parameters = new ArrayList<>();
//
//	@Min(3)
//	@Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
//	private Integer verbose = 1;
//
//	@Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
//	private String groups;
//
//	@Parameter(names = "-debug", description = "Debug mode")
//	private boolean debug = false;
//
//	@Override
//	public String toString() {
//		return "Args{" +
//			"parameters=" + parameters +
//			", verbose=" + verbose +
//			", groups='" + groups + '\'' +
//			", debug=" + debug +
//			'}';
//	}
//}