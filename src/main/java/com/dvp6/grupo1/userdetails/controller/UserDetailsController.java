package com.dvp6.grupo1.userdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.dvp6.grupo1.userdetails.model.ListEntry;
import com.dvp6.grupo1.userdetails.model.User;
import com.dvp6.grupo1.userdetails.model.UserRepository;
import com.dvp6.grupo1.userdetails.model.ListEntryRepository;
import com.google.gson.*;

@RestController
public class UserDetailsController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ListEntryRepository listEntryRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/api/user_details/{userId}")
    @ResponseBody
    public ResponseEntity<String> userDetails(@PathVariable String userId) {
        Optional<User> optionalUser;
        User user;
        optionalUser = userRepository.findById((long) Long.parseLong(userId));
        try{
            user = optionalUser.orElseThrow();
            return new ResponseEntity<String>(new Gson().toJson(user), HttpStatus.OK);
        }
        catch(Exception ex){
            return ReturnHttpError("User does not exist.", HttpStatus.NOT_FOUND);
        }    
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/api/user_details/{userId}", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> userDetailsPatch(@PathVariable String userId, @RequestBody String userData) {
        Optional<User> optionalUser;
        User user;
        optionalUser = userRepository.findById((long) Long.parseLong(userId));
        JsonObject jsonObject = new JsonParser().parse(userData).getAsJsonObject();
        System.out.println(userData);
        //System.out.println(userData.toJson());
        try{
            user = optionalUser.orElseThrow();
            boolean saveUser = false;
            if (!jsonObject.get("name").getAsString().equals("")) {
                user.setName(jsonObject.get("name").getAsString());
                saveUser = true;
            }
            if (!jsonObject.get("surname").getAsString().equals("")){
                user.setSurname(jsonObject.get("name").getAsString());
                saveUser = true;
            } 
            if (!jsonObject.get("gender").getAsString().equals("")){
                user.setGender(jsonObject.get("gender").getAsString());
                saveUser = true;
            } 
            if (!jsonObject.get("birth_date").getAsString().equals("")){
                user.setBirthDate(jsonObject.get("birth_date").getAsString());
                saveUser = true;
            } 
            if (!jsonObject.get("country").getAsString().equals("")){
                user.setCountry(jsonObject.get("country").getAsString());
                saveUser = true;
            } 

            if (saveUser == true) {
                User savedUser;
                savedUser = userRepository.save(user); 
                return new ResponseEntity<String>(new Gson().toJson(savedUser), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<String>(new Gson().toJson(user), HttpStatus.NOT_MODIFIED);
            }
        }
        catch(NullPointerException ex){
            return ReturnHttpError("There were imvalid values on the json provided, please review that and try again.", HttpStatus.BAD_REQUEST);
        }
        catch(IllegalArgumentException ex){
            return ReturnHttpError("There was problem while saving to the DB. Please make sure that all the fields are valid, specially userId.", HttpStatus.BAD_REQUEST);
        }
        catch(Exception ex){
            return ReturnHttpError("User does not exist.", HttpStatus.NOT_FOUND);
        }    
        
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/user_details", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> userDetailsPost(@PathVariable String userId, @RequestBody String userData) {
        Optional<User> optionalUser;
        JsonObject jsonObject = new JsonParser().parse(userData).getAsJsonObject();
        try{
            boolean validUser = false;
            if (!jsonObject.get("name").getAsString().equals("")) {
                validUser = true;
            }
            if (!jsonObject.get("surname").getAsString().equals("")){
                validUser = true;
            } 
            if (!jsonObject.get("gender").getAsString().equals("")){
                validUser = true;
            } 
            if (!jsonObject.get("birth_date").getAsString().equals("")){
                validUser = true;
            } 
            if (!jsonObject.get("country").getAsString().equals("")){
                validUser = true;
            }
            if (!jsonObject.get("id").getAsString().equals("")){
                validUser = true;
            }
            if (!jsonObject.get("password").getAsString().equals("")){
                validUser = true;
            } 

            if (validUser == true) {
                try{
                    CloseableHttpClient client = HttpClients.createDefault();
                    String SERVICE_NAME="some service";
                    HttpPost httpPost = new HttpPost("http://"+SERVICE_NAME+"/addUser");
                    String json = "{\"username\": \""+jsonObject.get("name").getAsString()+"\",\"password\": \""+jsonObject.get("password").getAsString()+"\",\"role\": \"ROLE_USER\",\"enabled\": true}";
                    StringEntity entity = new StringEntity(json);
                    httpPost.setEntity(entity);
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");
                    CloseableHttpResponse response = client.execute(httpPost);
                    ResponseEntity<String> authResponse;
                    if (response.getStatusLine().getStatusCode()==200){
                        User newUser = new User(jsonObject.get("name").getAsString(), 
                                                jsonObject.get("surname").getAsString(), 
                                                jsonObject.get("gender").getAsString(), 
                                                jsonObject.get("birth_date").getAsString(), 
                                                jsonObject.get("id").getAsString(), 
                                                jsonObject.get("country").getAsString());
                        newUser = userRepository.save(newUser);
                        authResponse = new ResponseEntity<String>("User " + newUser.getUserId() + " successfully created.", HttpStatus.OK);
                    }
                    else{
                        authResponse = ReturnHttpError("Failed to set user password, user creation aborted.", HttpStatus.NOT_MODIFIED);
                    }
                    client.close();
                    return authResponse;
                }
                catch(Exception e){
                    return new ResponseEntity<String>("Invalid user email provided.", HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }
            else{
                return new ResponseEntity<String>("There was an error creating the user, please check the values provided and try again.", HttpStatus.UNPROCESSABLE_ENTITY);
            }
        }
        catch(NullPointerException ex){
            return ReturnHttpError("There were imvalid values on the json provided, please review that and try again.", HttpStatus.BAD_REQUEST);
        }
        catch(IllegalArgumentException ex){
            return ReturnHttpError("There was problem while saving to the DB. Please make sure that all the fields are valid, specially userId.", HttpStatus.BAD_REQUEST);
        }
        catch(Exception ex){
            return ReturnHttpError("User does not exist.", HttpStatus.NOT_FOUND);
        }    
        
    }

    private ResponseEntity<String> ReturnHttpError(String message, HttpStatus returnStatus){
        Map<String, String> responseMessage = new HashMap<String,String>();
        //HttpStatus returnStatus = HttpStatus.BAD_REQUEST;
        responseMessage.put("erroCode", returnStatus.toString());
        responseMessage.put("Message", message);
        return new ResponseEntity<String>(new Gson().toJson(responseMessage), returnStatus);
    }

    private boolean EntryExists(JsonObject jsonObject){
        if (!jsonObject.get("entrytype").getAsString().equals("to_watch") && !jsonObject.get("entrytype").getAsString().equals("watched") && !jsonObject.get("entrytype").getAsString().equals("likes")){
            return false;
        }
        else return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/user_details/movie_list")
    @ResponseBody
    public ResponseEntity<String> addToList(@RequestBody String listEntryData) {
        Optional<ListEntry> optionalListEntry;
        ListEntry listEntry;
        JsonObject jsonObject = new JsonParser().parse(listEntryData).getAsJsonObject();

        if (EntryExists(jsonObject)==false){
            return ReturnHttpError("Invalid value informed to the EntryType field.", HttpStatus.BAD_REQUEST);
        }
                                                
        optionalListEntry = listEntryRepository.findByImdbidAndUseridAndEntrytype(jsonObject.get("imdbid").getAsString(), jsonObject.get("userid").getAsString(), jsonObject.get("entrytype").getAsString());
        try{
            listEntry = optionalListEntry.orElseThrow();
            return new ResponseEntity<String>("Entry already on user '"+ jsonObject.get("entrytype").getAsString() +"' list. To remove send a DELETE to this endpoint.", HttpStatus.OK);
        }
        catch(NoSuchElementException ex){
            //entry does not exist, let's add to customer list
            listEntryRepository.save(new ListEntry(jsonObject.get("imdbid").getAsString(), jsonObject.get("userid").getAsString(), new Date(System.currentTimeMillis()), jsonObject.get("entrytype").getAsString()));
            return new ResponseEntity<String>("Entry added to user '"+ jsonObject.get("entrytype").getAsString() +"' list.", HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/user_details/movie_list")
    @ResponseBody
    public ResponseEntity<String> deleteFromList(@RequestBody String listEntryData) {
        Optional<ListEntry> optionalListEntry;
        ListEntry listEntry;
        JsonObject jsonObject = new JsonParser().parse(listEntryData).getAsJsonObject();

        if (EntryExists(jsonObject)==false){
            return ReturnHttpError("Invalid value informed to the EntryType field.", HttpStatus.BAD_REQUEST);
        }
        optionalListEntry = listEntryRepository.findByImdbidAndUseridAndEntrytype(jsonObject.get("imdbid").getAsString(), jsonObject.get("userid").getAsString(), jsonObject.get("entrytype").getAsString());
        try{
            listEntry = optionalListEntry.orElseThrow();
            if (listEntry.getEntryType().equals("to_watch") || listEntry.getEntryType().equals("likes")){
                listEntryRepository.delete(listEntry);
                return new ResponseEntity<String>("Entry removed from user '"+ jsonObject.get("entrytype").getAsString() +"' list.", HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Item already on users whached list, therefore it cannot be removed.", HttpStatus.NOT_MODIFIED);
            }
        }
        catch(NoSuchElementException ex){
            //entry does not exist, let's add to customer list
            listEntryRepository.save(new ListEntry(jsonObject.get("imdbid").getAsString(), jsonObject.get("userid").getAsString(), new Date(System.currentTimeMillis()), jsonObject.get("entrytype").getAsString()));
            return new ResponseEntity<String>("Entry added to user '"+ jsonObject.get("entrytype").getAsString() +"' list.", HttpStatus.OK);
        }
        

    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/user_details/movie_list")
    @ResponseBody
    public ResponseEntity<String> getList(@RequestParam(name = "userid") String userId, @RequestParam(name = "list_type") String list_type, @RequestParam(name = "top") int top) {
        List<ListEntry> listEntries;
        switch(top){
            case 5:
                listEntries = listEntryRepository.findTopByUseridAndEntrytypeOrderByDateadded(userId, list_type);
                break;
            case 10:
                listEntries = listEntryRepository.findTop5ByUseridAndEntrytypeOrderByDateadded(userId, list_type);
                break;
            case 20: 
                listEntries = listEntryRepository.findTop10ByUseridAndEntrytypeOrderByDateadded(userId, list_type);
                break;
            default:
                listEntries = listEntryRepository.findTop20ByUseridAndEntrytypeOrderByDateadded(userId, list_type);
        }
        return new ResponseEntity<String>(new Gson().toJson(listEntries), HttpStatus.OK);
    }
}
