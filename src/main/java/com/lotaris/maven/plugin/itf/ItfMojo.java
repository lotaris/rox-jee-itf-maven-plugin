package com.lotaris.maven.plugin.itf;

import com.lotaris.maven.plugin.AbstractRoxConfigurableClientMojo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Integration Test Framework maven plugin to launch the integration 
 * tests from maven.
 *
 * @author Laurent Prévost, laurent.prevost@lotaris.com
 */
@Mojo(name = "itf")
public class ItfMojo extends AbstractRoxConfigurableClientMojo {
	private static final String UTF8 = "UTF-8";
	
	/**
	 * The URL of the server test API entry point.
	 */
	@Parameter(required = true)
	private String launchUrl;

	@Parameter
	private String category;
	
	/**
	 * Call the URL to launch the Integration Tests
	 * @throws IOException When the server is not reachable or error occurred
	 * @throws MalformedURLException When the URL configured is not valid
	 */
	@Override
	protected void run() throws MojoExecutionException {
		try {
			URL url = new URI(generateFinalUrl()).toURL();

			if (verbose) {
				getLog().info("URL to start the integration tests: " + url.toString());
			}

			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

			urlConn.setRequestMethod("GET");
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);

			if(urlConn.getResponseCode() == 200) {
				getLog().info("Tests successfully run, go to ROX to see the results or check the App Server logs.");
			}
			else {
				getLog().error("Unable to run the tests correctly. HTTP Return Code: " + urlConn.getResponseCode());
			}
		}
		catch (Exception e) {
			throw new MojoExecutionException("Unable to run the integration tests", e);
		}
	}
	
	/**
	 * Generate the URL to use to launch the integration tests
	 * @return The URL generated
	 * @exception UnsupportedEncodingException If UTF-8 is not available
	 */
	private String generateFinalUrl() throws UnsupportedEncodingException {
		String finalUrl = launchUrl;

		if (filters == null) {
			filters = new ArrayList<String>();
		}
		
		if (!filters.isEmpty()) {
			finalUrl = finalUrl.replaceAll("\\{filters\\}", URLEncoder.encode(buildListParameters(filters), UTF8));
		}
		else {
			finalUrl = finalUrl.replaceAll("\\{filters\\}", "");
		}
	
		if (seed != null) {
			finalUrl = finalUrl.replaceAll("\\{seed\\}", URLEncoder.encode("" + seed, UTF8));
		}
		else {
			finalUrl = finalUrl.replaceAll("\\{seed\\}", "");
		}
		
		if (category != null) {
			finalUrl = finalUrl.replaceAll("\\{category\\}", URLEncoder.encode(category, UTF8));
		}
		else {
			finalUrl = finalUrl.replaceAll("\\{category\\}", "");
		}
		
		return finalUrl;
	}	
	
	/**
	 * Create a comma separated list to create an URL parameter
	 * @param paramValues The list of the values
	 * @return The parameter created to add to an URL
	 * @exception UnsupportedEncodingException If UTF-8 is not available
	 */
	private String buildListParameters(List<String> paramValues) throws UnsupportedEncodingException {
		StringBuilder paramBuilder = new StringBuilder();

		for (String paramValue : paramValues) {
			paramBuilder.append(URLEncoder.encode(paramValue, "UTF-8")).append(",");
		}

		return paramBuilder.toString().replaceAll(",$", "");
	}	
}
