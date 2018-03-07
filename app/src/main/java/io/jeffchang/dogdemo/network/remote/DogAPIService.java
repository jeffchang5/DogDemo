package io.jeffchang.dogdemo.network.remote;


import io.jeffchang.dogdemo.models.DogListResponse;
import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Service that defines the RESTful interactions with the API.
 */

public interface DogAPIService {

    @GET("breeds/list/all")
    Single<DogListResponse> getDogBreedResponse();

}
