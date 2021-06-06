package net.jonteohr.referee.events;

import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;

public class SlashCommand extends ListenerAdapter {
	@Override
	public void onSlashCommand(SlashCommandEvent e) {
		if(e.getGuild() == null)
			return;

		switch(e.getName()) {
			case "referral":
				createInvite(e);
				break;
			default:
				e.reply("Could not process that command. Try again later!").setEphemeral(true).queue();
		}
	}

	private void createInvite(SlashCommandEvent e) {
		e.deferReply(true).queue();
		InteractionHook hook = e.getHook();
		hook.setEphemeral(true);

		// TODO Check if user already has registered referral code for this guild

		Invite invite = e.getGuild().getDefaultChannel().createInvite()
				.setTemporary(false)
				.setMaxAge(0)
				.setUnique(true)
				.complete();

		// TODO save in DB who the owner of this code is

		hook.sendMessage("Here is your personalized invite link: https://discord.gg/" + invite.getCode()).queue();
	}
}
