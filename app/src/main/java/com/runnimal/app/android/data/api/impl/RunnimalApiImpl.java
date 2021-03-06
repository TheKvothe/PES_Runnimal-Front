package com.runnimal.app.android.data.api.impl;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.runnimal.app.android.data.api.RunnimalApi;
import com.runnimal.app.android.domain.Friend;
import com.runnimal.app.android.domain.FriendRequest;
import com.runnimal.app.android.domain.Friendship;
import com.runnimal.app.android.domain.Owner;
import com.runnimal.app.android.domain.Pet;
import com.runnimal.app.android.domain.Point;
import com.runnimal.app.android.domain.Ranking;
import com.runnimal.app.android.domain.Training;
import com.runnimal.app.android.domain.User;
import com.runnimal.app.android.util.JacksonFactory;
import com.runnimal.app.android.util.SingletonSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import lombok.SneakyThrows;

public class RunnimalApiImpl extends AbstractApiClient implements RunnimalApi {

    private final JacksonFactory jacksonFactory;

    @Inject
    public RunnimalApiImpl(RequestQueue requestQueue, JacksonFactory jacksonFactory) {
        super(requestQueue);
        this.jacksonFactory = jacksonFactory;
    }

    @Override
    @SneakyThrows
    public void login(String email, String password, RunnimalApiCallback<String> callback) {
        JSONObject jsonBody = new JSONObject() //
                .put("email", email) //
                .put("password", password);
        post("http://nidorana.fib.upc.edu/api/login/", jsonBody, callback);
    }

    @Override
    public void listTrainings(RunnimalApiCallback<List<Training>> callback) {
        get("http://nidorana.fib.upc.edu/api/trainnings", //
                response -> {
                    return jacksonFactory.toList(response, Training.class);

                }, //
                callback);
    }

    @Override
    public void getTraining(String id, RunnimalApiCallback<Training> callback) {
        get("http://nidorana.fib.upc.edu/api/trainnings/" + id, //
                response -> {
                    return jacksonFactory.toObject(response, Training.class);

                }, //
                callback);
    }

    @Override
    public void listRankings(RunnimalApiCallback<List<Ranking>> callback) {
        get("http://nidorana.fib.upc.edu/api/ranking", //
                response -> {
                    return jacksonFactory.toList(response, Ranking.class);

                }, //
                callback);
    }

    @Override
    public void listPets(String ownerEmail, RunnimalApiCallback<List<Pet>> callback) {
        String email = ownerEmail != null ? ownerEmail : SingletonSession.Instance().getMail();
        get("http://nidorana.fib.upc.edu/api/pets/user/" + email, //
                response -> {
                    return jacksonFactory.toList(response, Pet.class);

                }, //
                callback);
    }

    @Override
    public void getPet(String id, String email, RunnimalApiCallback<Pet> callback) {
        get("http://nidorana.fib.upc.edu/api/pets/" + email + "/" + id, //
                response -> {
                    return jacksonFactory.toObject(response, Pet.class);

                }, //
                callback);
    }

    @Override
    @SneakyThrows
    public void modifyPet(Pet pet, RunnimalApiCallback<String> callback) {
        String url = "http://nidorana.fib.upc.edu/api/pets/" + SingletonSession.Instance().getMail() + "/" + pet.getName();

        pet.setId(null);
        JSONObject jsonBody = new JSONObject(jacksonFactory.toJsonNode(pet).toString());
        put(url, jsonBody, callback);
    }

    @Override
    @SneakyThrows
    public void createPet(Pet pet, RunnimalApiCallback<String> callback) {
        JSONObject jsonBody = new JSONObject(jacksonFactory.toJsonNode(pet).toString()) //
                .put("owner", SingletonSession.Instance().getMail());
        post("http://nidorana.fib.upc.edu/api/pets/", jsonBody, callback);
    }

    @Override
    @SneakyThrows
    public void deletePet(String email, String petName, RunnimalApiCallback<String> callback) {
        String url = "http://nidorana.fib.upc.edu/api/pets/" + email + "/" + petName;
        Log.d("refactor", "deletePet: " + url);
        delete(url, callback);
    }

    @Override
    public void listOwners(RunnimalApiCallback<List<Owner>> callback) {
        get("http://nidorana.fib.upc.edu/api/users", //
                response -> {
                    return jacksonFactory.toList(response, Owner.class);
                }, //
                callback);
    }

    @Override
    @SneakyThrows
    public void addPoint(int points, String email, RunnimalApiCallback<String> callback) {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("points", points);

        Log.d("refactor", "http://nidorana.fib.upc.edu/api/users/" + email + "/addpoints");
        put("http://nidorana.fib.upc.edu/api/users/" + email + "/addpoints", jsonBody, callback);
    }

    @Override
    public void listLocalRanking(RunnimalApiCallback<List<Ranking>> callback) {
        get("http://nidorana.fib.upc.edu/api/users/"+SingletonSession.Instance().getMail()+"/ranking", //http://nidorana.fib.upc.edu/api/users/ash@pokemon.com/ranking

                response -> {
                    return jacksonFactory.toList(response, Ranking.class);

                }, //
                callback);
        Log.d("jajas","http://nidorana.fib.upc.edu/apiusers/"+SingletonSession.Instance().getMail()+"/ranking" );

    }

    @Override
    public void listRequests(RunnimalApiCallback<List<Owner>> listRunnimalApiCallback) {

    }

    @Override
    public void listFriendship(RunnimalApiCallback<List<Friendship>> listRunnimalApiCallback) {

    }

    @Override
    public void listFriendshipRequests(RunnimalApiCallback<List<Friendship>> listRunnimalApiCallback) {
        get("http://nidorana.fib.upc.edu/api/users/" + SingletonSession.Instance().getMail() + "/friendRequests", //
                response -> {
                    return jacksonFactory.toList(response, Friendship.class);

                }, //
                listRunnimalApiCallback);
    }

    @Override
    public void rejectFriendship(String id, RunnimalApiCallback<String> callback) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("action", "deny");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        put("http://nidorana.fib.upc.edu/api/friends/" + id, jsonBody, callback);
    }


    @Override
    public void acceptFriend(String id, RunnimalApiCallback<String> callback) {

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("action", "accept");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        put("http://nidorana.fib.upc.edu/api/friends/" + id, jsonBody, callback);
    }


    @Override
    public void getOwner(String email, RunnimalApiCallback<Owner> callback) {
        get("http://nidorana.fib.upc.edu/api/users/" + email, //
                response -> {
                    return jacksonFactory.toObject(response, Owner.class);

                }, //
                callback);
    }

    @Override
    @SneakyThrows
    public void modifyOwner(Owner owner, RunnimalApiCallback<String> callback) {
        String url = "http://nidorana.fib.upc.edu/api/users/" + SingletonSession.Instance().getMail();

        owner.setId(null);
        owner.setEmail(null);
        JSONObject jsonBody = new JSONObject(jacksonFactory.toJsonNode(owner).toString());

        put(url, jsonBody, callback);
    }

    @Override
    @SneakyThrows
    public void createOwner(Owner owner, RunnimalApiCallback<String> callback) {
        post("http://nidorana.fib.upc.edu/api/login/", new JSONObject(jacksonFactory.toJsonNode(owner).toString()), callback);
    }


    @Override
    @SneakyThrows
    public void deleteOwner(String id, String mail, RunnimalApiCallback<String> callback) {
        String url = "http://nidorana.fib.upc.edu/api/pets/" + id + "/owners";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("userEmail", mail);

        put(url, jsonBody, callback);
    }

    @Override
    @SneakyThrows
    public void addOwner(String petId, String email, RunnimalApiCallback<String> callback) {
        String url = "http://nidorana.fib.upc.edu/api/pets/" + petId + "/owners";

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("userEmail", email);

        post(url, jsonBody, callback);
    }

    @Override
    public void getFriendRequests(String ownerEmail, RunnimalApiCallback<List<FriendRequest>> callback) {
        get("http://nidorana.fib.upc.edu/api/" + ownerEmail + "/friendRequests", //
                response -> {
                    return jacksonFactory.toList(response, FriendRequest.class);

                }, //
                callback);
    }

    @Override
    public void isFriend(String friendEmail, RunnimalApiCallback<Boolean> callback) {
        get("http://nidorana.fib.upc.edu/api/friends/" + SingletonSession.Instance().getMail() + "/" + friendEmail, //
                response -> {
                    // TODO
                    return true;

                }, //
                callback);
    }

    @Override
    @SneakyThrows
    public void createFriendRequest(String requestedEmail, RunnimalApiCallback<String> callback) {
        JSONObject jsonBody = new JSONObject() //
                .put("requestingEmail", SingletonSession.Instance().getMail()) //
                .put("requestedEmail", requestedEmail);
        post("http://nidorana.fib.upc.edu/api/friendRequests/new", jsonBody, callback);
    }

    @Override
    public void deleteFriend(String ownerId, RunnimalApiCallback<String> callback) {
        delete("http://nidorana.fib.upc.edu/api/friends/" + ownerId, callback);
    }

    @Override
    public void listPoints(RunnimalApiCallback<List<Point>> callback) {
        get("http://nidorana.fib.upc.edu/api/points", //
                response -> {
                    return jacksonFactory.toList(response, Point.class);

                }, //
                callback);
    }


    ///OTROS?/////

    @Override
    public void listFriends(RunnimalApiCallback<List<Friend>> listRunnimalApiCallback) {

    }

    @Override
    public void getFriend(String id, RunnimalApiCallback<Friend> friendRunnimalApiCallback) {

    }

    @Override
    public void listUsers(RunnimalApiCallback<List<User>> listRunnimalApiCallback) {

    }

    @Override
    public void getUser(String id, RunnimalApiCallback<User> userRunnimalApiCallback) {

    }

}
