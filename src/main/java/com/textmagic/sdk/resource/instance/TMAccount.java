package com.textmagic.sdk.resource.instance;

import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.RestResponse;
import com.textmagic.sdk.resource.Resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TMAccount extends Resource<RestClient> {

    /**
     * Resource properties
     */
	private Map<String, Object> properties;
	
    /**
     * Instantiates account
     *
     * @param client HTTP client
     */
    public TMAccount(final RestClient client) {
        super(client);
        this.properties = new HashMap<String, Object>();
        this.requestFields = new HashSet<String>(
        	Arrays.asList(
        		new String[] {"firstName", "lastName", "company"}
        	)
        );
    }
    
    @Override
    protected String getResourcePath() {
        return "user";
    }

	/**
	 * Get account
	 *
	 * @throws RestException
	 */
	public boolean get() throws RestException {
		RestResponse response = getClient().request(getResourcePath(), "GET");
        this.properties = new HashMap<String, Object>(response.toMap());
        return !response.isError();
	}
	
	/**
	 * Update account
	 *
	 * @throws RestException
	 */
	public boolean update() throws RestException {
		getClient().request(getResourcePath(), "PUT", buildRequestParameters(properties));
		this.properties = new HashMap<String, Object>();
        return get();
	}    
    
	/**
	 * Retrieve resource property
	 *
	 * @param name Property name
	 * @return Property value
	 */
	protected Object getProperty(String name) {
		return properties.get(name);
	}
	
    /**
	 * Set resource property
	 *
	 * @param Property name
	 * @param Property value
	 */
	private void setProperty(String name, Object value) {
		properties.put(name, value);
	}
    
    /**
     * Retrieve id
     *
     * @return id
     */
    public Integer getId() {
        return (Integer) getProperty("id");
    }
    
    /**
     * Retrieve username
     *
     * @return username
     */
    public String getUsername() {
        return (String) getProperty("username");
    }
    
    /**
     * Retrieve firstName
     *
     * @return firstName
     */
    public String getFirstName() {
        return (String) getProperty("firstName");
    }
    
    /**
     * Set firstName
     */
    public void setFirstName(String firstName) {
        setProperty("firstName", firstName);
    }    
    
    /**
     * Retrieve lastName
     *
     * @return lastName
     */
    public String getLastName() {
        return (String) getProperty("lastName");
    }
    
    /**
     * Set lastName
     */
    public void setLastName(String lastName) {
        setProperty("lastName", lastName);
    }    
    
    /**
     * Retrieve company
     *
     * @return company
     */
    public String getCompany() {
        return (String) getProperty("company");
    }

    /**
     * Set company
     */
    public void setCompany(String company) {
        setProperty("company", company);
    }    
    
    /**
     * Retrieve status
     *
     * @return status
     */
    public String getStatus() {
        return (String) getProperty("status");
    }
    
    /**
     * Retrieve balance
     *
     * @return balance
     */
    public Double getBalance() {
        Object value = getProperty("balance");
    	if (value instanceof Integer) {
    		return ((Integer) value).doubleValue();
    	}

    	return (Double) getProperty("balance");
    }
    
    /**
     * Retrieve subaccountType
     *
     * @return subaccountType
     */
    public String getSubaccountType() {
        return (String) getProperty("subaccountType");
    }
    
    /**
     * Retrieve currency
     *
     * @return currency
     */
    @SuppressWarnings("unchecked")
	public String getCurrency() {
        Map<String, Object> currency = (Map<String, Object>) getProperty("currency");
        
        return (String) currency.get("id");
    }
}
