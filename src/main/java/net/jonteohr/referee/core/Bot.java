package net.jonteohr.referee.core;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.jonteohr.referee.Configuration;
import net.jonteohr.referee.events.SlashCommand;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bot {
	private static JDA jda;

	public static List<Code> codeList = new ArrayList<>();

	public void start() throws LoginException {
		Collection<GatewayIntent> intents = new ArrayList<>();
		intents.add(GatewayIntent.GUILD_INVITES);
		intents.add(GatewayIntent.GUILD_PRESENCES);
		intents.add(GatewayIntent.GUILD_MEMBERS);

		jda = JDABuilder.create(Configuration.BOT_TOKEN, intents)
				.disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE)
				.addEventListeners(new SlashCommand())
				.build();

		// Only update slash commands if bot is live
		if(!Configuration.DEV_ENV) {
			CommandListUpdateAction commandList = jda.updateCommands();

			commandList.addCommands(
					new CommandData("referral", "Fetches your personalized referral invite link.")
			);

			commandList.queue();
		}

		// TODO Fetch all data from database and store into codeList
	}

	public static JDA getJDA() {
		return jda;
	}
}
