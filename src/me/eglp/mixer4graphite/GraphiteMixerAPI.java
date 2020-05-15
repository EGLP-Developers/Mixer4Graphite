package me.eglp.mixer4graphite;

import java.util.concurrent.ExecutionException;

import com.mixer.api.MixerAPI;
import com.mixer.api.resource.MixerUser;
import com.mixer.api.services.impl.UsersService;

public class GraphiteMixerAPI {

	public static final String
		ENDPOINT = "https://mixer.com/api/v1/resources/",
		OAUTH_ENDPOINT = "https://mixer.com/oauth/authorize";
	
	private String clientID, clientSecret;
	private MixerAPI api;
//	private ExecutorService executor;
	
	public GraphiteMixerAPI(String clientID, String clientSecret) {
		this.clientID = clientID;
		this.clientSecret = clientSecret;
		this.api = new MixerAPI(clientID);
//		this.executor = Executors.newCachedThreadPool();
	}
	
	public String getClientID() {
		return clientID;
	}
	
	public String getClientSecret() {
		return clientSecret;
	}
	
	public MixerUser searchByName(String name) {
		try {
			return api.use(UsersService.class).search(name).get().stream().findFirst().orElse(null);
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
	
//	private <T> CompletableFuture<T> wrap(ListenableFuture<T> future) {
//		CompletableFuture<T> f = new CompletableFuture<>();
//		Futures.addCallback(future, new ResponseHandler<T>() {
//
//			@Override
//			public void onSuccess(T result) {
//				f.complete(result);
//			}
//			
//			@Override
//			public void onFailure(Throwable throwable) {
//				f.completeExceptionally(throwable);
//			}
//			
//		}, executor);
//		return f;
//	}
	
}