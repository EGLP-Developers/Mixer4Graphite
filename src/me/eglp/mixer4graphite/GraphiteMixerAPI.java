package me.eglp.mixer4graphite;

import com.mixer.api.MixerAPI;
import com.mixer.api.resource.MixerUser;
import com.mixer.api.resource.channel.MixerChannel;
import com.mixer.api.services.impl.ChannelsService;
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
	
	public MixerUser getUserByName(String name) {
		try {
			MixerUser u = api.use(UsersService.class).search(name).get().stream().findFirst().orElse(null);
			if(!u.username.equalsIgnoreCase(name)) return null;
			return u;
		} catch (Exception e) {
			return null;
		}
	}
	
	public MixerUser getUserByID(int id) {
		try {
			return api.use(UsersService.class).findOne(id).get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public MixerChannel getChannelByID(int id) {
		try {
			return api.use(ChannelsService.class).findOne(id).get();
		} catch (Exception e) {
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