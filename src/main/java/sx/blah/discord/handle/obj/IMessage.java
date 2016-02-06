package sx.blah.discord.handle.obj;

import sx.blah.discord.api.DiscordException;
import sx.blah.discord.api.MissingPermissionsException;
import sx.blah.discord.util.HTTP429Exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Represents a discord message.
 */
public interface IMessage {
	
	/**
	 * Gets the string content of the message.
	 *
	 * @return The content of the message
	 */
	String getContent();
	
	/**
	 * Gets the channel that this message belongs to.
	 *
	 * @return The channel.
	 */
	IChannel getChannel();
	
	/**
	 * Gets the user who authored this message.
	 *
	 * @return The author.
	 */
	IUser getAuthor();
	
	/**
	 * Gets the message id.
	 *
	 * @return The id.
	 */
	String getID();
	
	/**
	 * Gets the timestamp for when this message was sent/edited.
	 *
	 * @return The timestamp.
	 */
	LocalDateTime getTimestamp();
	
	/**
	 * Gets the users mentioned in this message.
	 *
	 * @return The users mentioned.
	 */
	List<IUser> getMentions();
	
	/**
	 * Gets the attachments in this message.
	 *
	 * @return The attachments.
	 */
	List<Attachment> getAttachments();
	
	/**
	 * Adds an "@mention," to the author of the referenced Message
	 * object before your content
	 *
	 * @param content Message to send.
	 * 
	 * @throws MissingPermissionsException
	 * @throws HTTP429Exception
	 * @throws DiscordException
	 */
	void reply(String content) throws MissingPermissionsException, HTTP429Exception, DiscordException;
	
	/**
	 * Edits the message. NOTE: Discord only supports editing YOUR OWN messages!
	 *
	 * @param content The new content for the message to contain.
	 * @return The new message (this).
	 *
	 * @throws MissingPermissionsException
	 * @throws DiscordException
	 */
	IMessage edit(String content) throws MissingPermissionsException, HTTP429Exception, DiscordException;
	
	/**
	 * Returns whether this message mentions everyone.
	 * 
	 * @return True if it mentions everyone, false if otherwise.
	 */
	boolean mentionsEveryone();
	
	/**
	 * Deletes the message.
	 *
	 * @throws MissingPermissionsException
	 * @throws HTTP429Exception
	 * @throws DiscordException
	 */
	void delete() throws MissingPermissionsException, HTTP429Exception, DiscordException;
	
	/**
	 * Acknowledges a message and all others before it (marks it as "read").
	 * 
	 * @throws HTTP429Exception
	 * @throws DiscordException
	 */
	void acknowledge() throws HTTP429Exception, DiscordException;
	
	/**
	 * Checks if the message has been read by this account.
	 *
	 * @return True if the message has been read, false if otherwise.
	 */
	boolean isAcknowledged();
	
	/**
	 * Gets the time that this message was last edited.
	 * 
	 * @return The edited timestamp.
	 */
	Optional<LocalDateTime> getEditedTimestamp();
	
	/**
	 * Represents an attachment included in the message.
	 */
	class Attachment {
		
		/**
		 * The file name of the attachment.
		 */
		protected final String filename;
		
		/**
		 * The size, in bytes of the attachment.
		 */
		protected final int filesize;
		
		/**
		 * The attachment id.
		 */
		protected final String id;
		
		/**
		 * The download link for the attachment.
		 */
		protected final String url;
		
		public Attachment(String filename, int filesize, String id, String url) {
			this.filename = filename;
			this.filesize = filesize;
			this.id = id;
			this.url = url;
		}
		
		/**
		 * Gets the file name for the attachment.
		 *
		 * @return The file name of the attachment.
		 */
		public String getFilename() {
			return filename;
		}
		
		/**
		 * Gets the size of the attachment.
		 *
		 * @return The size, in bytes of the attachment.
		 */
		public int getFilesize() {
			return filesize;
		}
		
		/**
		 * Gets the id of the attachment.
		 *
		 * @return The attachment id.
		 */
		public String getId() {
			return id;
		}
		
		/**
		 * Gets the direct link to the attachment.
		 *
		 * @return The download link for the attachment.
		 */
		public String getUrl() {
			return url;
		}
	}
}
