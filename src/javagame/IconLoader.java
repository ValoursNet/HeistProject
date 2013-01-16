package javagame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;


//MJ: Code found like the fiend I am. Does the job.
public class IconLoader
{
	/*************************************************************************
	 * Loads an icon in ByteBuffer form.
	 * 
	 * @param loader
	 *            A Loader pointing to the image.
	 * 
	 * @return An array of ByteBuffers containing the pixel data for the icon in
	 *         various sizes (as recommended by the OS).
	 *************************************************************************/
	public ByteBuffer[] load()
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File("res/32.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		ByteBuffer[] buffers = null;
		String OS = System.getProperty("os.name").toUpperCase();
		if(OS.contains("WIN"))
		{
			buffers = new ByteBuffer[2];
			buffers[0] = loadInstance(image, 16);
			buffers[1] = loadInstance(image, 32);
		}
		else if(OS.contains("MAC"))
		{
			buffers = new ByteBuffer[1];
			buffers[0] = loadInstance(image, 128);
		}
		else
		{
			buffers = new ByteBuffer[1];
			buffers[0] = loadInstance(image, 32);
		}
		return buffers;
	}

	/*************************************************************************
	 * Copies the supplied image into a square icon at the indicated size.
	 * 
	 * @param image
	 *            The image to place onto the icon.
	 * @param dimension
	 *            The desired size of the icon.
	 * 
	 * @return A ByteBuffer of pixel data at the indicated size.
	 *************************************************************************/
	private static ByteBuffer loadInstance(BufferedImage image, int dimension)
	{
		BufferedImage scaledIcon = new BufferedImage(dimension, dimension,
				BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = scaledIcon.createGraphics();
		double ratio = getIconRatio(image, scaledIcon);
		double width = image.getWidth() * ratio;
		double height = image.getHeight() * ratio;
		g.drawImage(image, (int) ((scaledIcon.getWidth() - width) / 2),
				(int) ((scaledIcon.getHeight() - height) / 2), (int) (width),
				(int) (height), null);
		g.dispose();

		return convertToByteBuffer(scaledIcon);
	}

	/*************************************************************************
	 * Gets the width/height ratio of the icon. This is meant to simplify
	 * scaling the icon to a new dimension.
	 * 
	 * @param src
	 *            The base image that will be placed onto the icon.
	 * @param icon
	 *            The icon that will have the image placed on it.
	 * 
	 * @return The amount to scale the source image to fit it onto the icon
	 *         appropriately.
	 *************************************************************************/
	private static double getIconRatio(BufferedImage src, BufferedImage icon)
	{
		double ratio = 1;
		if (src.getWidth() > icon.getWidth())
			ratio = (double) (icon.getWidth()) / src.getWidth();
		else
			ratio = (int) (icon.getWidth() / src.getWidth());
		if (src.getHeight() > icon.getHeight())
		{
			double r2 = (double) (icon.getHeight()) / src.getHeight();
			if (r2 < ratio)
				ratio = r2;
		}
		else
		{
			double r2 = (int) (icon.getHeight() / src.getHeight());
			if (r2 < ratio)
				ratio = r2;
		}
		return ratio;
	}

	/*************************************************************************
	 * Converts a BufferedImage into a ByteBuffer of pixel data.
	 * 
	 * @param image
	 *            The image to convert.
	 * 
	 * @return A ByteBuffer that contains the pixel data of the supplied image.
	 *************************************************************************/
	public static ByteBuffer convertToByteBuffer(BufferedImage image)
	{
		byte[] buffer = new byte[image.getWidth() * image.getHeight() * 4];
		int counter = 0;
		for (int i = 0; i < image.getHeight(); i++)
			for (int j = 0; j < image.getWidth(); j++)
			{
				int colorSpace = image.getRGB(j, i);
				buffer[counter + 0] = (byte) ((colorSpace << 8) >> 24);
				buffer[counter + 1] = (byte) ((colorSpace << 16) >> 24);
				buffer[counter + 2] = (byte) ((colorSpace << 24) >> 24);
				buffer[counter + 3] = (byte) (colorSpace >> 24);
				counter += 4;
			}
		return ByteBuffer.wrap(buffer);
	}
}