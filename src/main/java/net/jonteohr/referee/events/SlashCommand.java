package net.jonteohr.referee.events;

import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.jonteohr.referee.core.Bot;
import net.jonteohr.referee.core.Code;

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

		boolean exists = false;
		Code userCode = null;

		for(Code code : Bot.codeList) {
			if(code.getUserId().equalsIgnoreCase(e.getUser().getId()) && code.getGuildId().equalsIgnoreCase(e.getGuild().getId())) {
				exists = true;
				userCode = new Code(
						code.getCode(),
						e.getGuild().getId(),
						code.getUserId(),
						code.getUses()
				);
				break;
			}
		}

		if(!exists) { // The user does not have a referral code yet!
			Invite invite = e.getGuild().getDefaultChannel().createInvite()
					.setTemporary(false)
					.setMaxAge(0)
					.setUnique(true)
					.complete();

			userCode = new Code(
					invite.getCode(),
					e.getGuild().getId(),
					e.getUser().getId(),
					invite.getUses()
			);

			Bot.codeList.add(userCode);

			hook.sendMessage("Here is your personalized invite link: https://discord.gg/" + userCode.getCode()).queue();
			return;
		}

		hook.sendMessage("Here is your current referral code: " + userCode.getCode() + " (https://discord.gg/" + userCode.getCode() + ").\n" +
				"You currently got " + userCode.getUses() + " referrals.").queue();
	}
}
