package com.zzcm.fourgad.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码生成器
 * @author qiulongjie
 *
 */
public class RandImgCreater {
	private static final String CODE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	private HttpServletResponse response = null;
	private static final int HEIGHT = 20;
	private static final int FONT_NUM = 4;
	private int width = 0;
	private int iNum = 0;
	private String codeList = "";
	private boolean drawBgFlag = false;

	private int rBg = 0;
	private int gBg = 0;
	private int bBg = 0;
	
	private boolean drawFontFlag = false;
	
	private int rFont = 0;
	private int gFont = 0;
	private int bFont = 0;

	public RandImgCreater(HttpServletResponse response) {
		this.response = response;
		this.width = 13 * FONT_NUM + 12;
		this.iNum = FONT_NUM;
		this.codeList = CODE_LIST;
	}

	public RandImgCreater(HttpServletResponse response, int iNum,
			String codeList) {
		this.response = response;
		this.width = 13 * iNum + 12;
		this.iNum = iNum;
		this.codeList = codeList;
	}

	public String createRandImage() {
		BufferedImage image = new BufferedImage(width, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		if (drawBgFlag) {
			g.setColor(new Color(rBg, gBg, bBg));
			g.fillRect(0, 0, width, HEIGHT);
		} else {
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, HEIGHT);

			for (int i = 0; i < 155; i++) {
				g.setColor(getRandColor(140, 200));
				int x = random.nextInt(width);
				int y = random.nextInt(HEIGHT);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
		}

		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		String sRand = "";
		for (int i = 0; i < iNum; i++) {
			int rand = random.nextInt(codeList.length());
			String strRand = codeList.substring(rand, rand + 1);
			sRand += strRand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(strRand, 13 * i + 6, 16);
		}
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			//ImageIO.write(image, "JPEG", new File("F:\\MyEclipse\\Workspaces2\\MyEclipse 8.6\\Yangzhengma\\WebRoot\\img.jpeg"));
		} catch (IOException e) {

		}

		return sRand;
	}
	
	public String createCornerRadiusImage(int cornerRadius) {
		BufferedImage image = new BufferedImage(width, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		Random random = new Random();
		g.setComposite(AlphaComposite.Src); 
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON); 
		if (drawBgFlag) {
			g.setColor(new Color(rBg, gBg, bBg));
			g.fill(new RoundRectangle2D.Float(0, 0, width, HEIGHT, cornerRadius, 
	                cornerRadius)); 
		} else {
			g.setColor(getRandColor(200, 250));
			g.fill(new RoundRectangle2D.Float(0, 0, width, HEIGHT, cornerRadius, 
	                cornerRadius)); 
			
			for (int i = 0; i < 155; i++) {
				g.setColor(getRandColor(140, 200));
				int x = random.nextInt(width);
				int y = random.nextInt(HEIGHT);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
		}
		g.setComposite(AlphaComposite.SrcAtop); 
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		String sRand = "";
		for (int i = 0; i < iNum; i++) {
			int rand = random.nextInt(codeList.length());
			String strRand = codeList.substring(rand, rand + 1);
			sRand += strRand;
			if(drawFontFlag){
				g.setColor(new Color(rFont,gFont,bFont));
			}else{
				g.setColor(new Color(20 + random.nextInt(110), 20 + random
						.nextInt(110), 20 + random.nextInt(110)));
			}
			g.drawString(strRand, 13 * i + 6, 16);
		}
		g.drawImage(image, 0, 0, null);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			//ImageIO.write(image, "JPEG", new File("F:\\MyEclipse\\Workspaces2\\MyEclipse 8.6\\Yangzhengma\\WebRoot\\img.jpeg"));
		} catch (IOException e) {
			
		}
		
		return sRand;
	}
	public String createCornerRadiusImage2(int cornerRadius) {
		BufferedImage image = new BufferedImage(width, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		g.setComposite(AlphaComposite.Src); 
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON); 
		g.setColor(new Color(rBg, gBg, bBg));
		g.fill(new RoundRectangle2D.Float(0, 0, width, HEIGHT, cornerRadius, 
					cornerRadius)); 
		g.setComposite(AlphaComposite.SrcAtop); 
		
		g.drawImage(image, 0, 0, null);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
			//ImageIO.write(image, "JPEG", new File("F:\\MyEclipse\\Workspaces2\\MyEclipse 8.6\\Yangzhengma\\WebRoot\\img.jpeg"));
		} catch (IOException e) {
			
		}
		
		return "";
	}

	public void setBgColor(int r, int g, int b) {
		drawBgFlag = true;
		this.rBg = r;
		this.gBg = g;
		this.bBg = b;
	}
	
	public void setFontColor(int r, int g, int b) {
		drawFontFlag = true;
		this.rFont = r;
		this.gFont = g;
		this.bFont = b;
	}

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
