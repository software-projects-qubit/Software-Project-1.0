package com.wits.witssrcconnect.utils;


import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


/**
 *
 * @author steve
 */
public class Ldap {

    private LdapContext context; 
    private boolean authenticationError = false;
    private Exception connectionError;

    public static Ldap connect(String domain, String username, String password) {
        return new Ldap(domain, username, password);
    }

    private Ldap(String domain, String username, String password) {

        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://ss.wits.ac.za:389");
            //
            env.put(Context.SECURITY_AUTHENTICATION, "simple");

            env.put(Context.SECURITY_PRINCIPAL, domain + "\\" + username);
            env.put(Context.SECURITY_CREDENTIALS, password);

            // Create the initial context
            context = new InitialLdapContext(env, null);
        } catch (AuthenticationException e) {
            authenticationError = true;
        } catch (Exception ex) {
            connectionError = ex;
        }
    }

    public void close() {
        if (context != null) {
            try {
                context.close();
            } catch (NamingException ex) {
                Logger.getLogger(Ldap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isAuthenticationError() {
        return authenticationError;
    }

    public Exception getConnectionError() {
        return connectionError;
    }

    public Person getUser(String username) {
        Person person = new Person();
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String[] attrIDs = {"distinguishedName",
                "sn",
                "givenname",
                "mail",
                "telephonenumber"};
            constraints.setReturningAttributes(attrIDs);
            //First input parameter is search bas, it can be "CN=Users,DC=YourDomain,DC=com"
            //Second Attribute can be uid=username
            NamingEnumeration answer = context.search("ou=students,ou=wits university,dc=ss,dc=wits,dc=ac,dc=za", "uid="
                    + username, constraints);
            if (answer.hasMore()) {
                Attributes attrs = ((SearchResult) answer.next()).getAttributes();
                String id = attrs.get("distinguishedName").get(0).toString();
                id = id.substring(id.indexOf('=') + 1, id.indexOf(',')); 
                person.setId(id);

                String name = attrs.get("givenname").get(0).toString() + " " +
                        attrs.get("sn").get(0).toString();
                person.setFirstname(attrs.get("givenname").get(0).toString());
                person.setLastname(attrs.get("sn").get(0).toString());  
                person.setName(name);
                person.setEmail(attrs.get("mail").get(0).toString());
                person.setAccessLevel(Person.STUDENT);
            } else {
                return null;
            }

        } catch (Exception ex) {
            return null;
        }
        return person;
    }

}