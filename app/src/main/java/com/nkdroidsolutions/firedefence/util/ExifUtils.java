package com.nkdroidsolutions.firedefence.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 public class ExifUtils {

	public static Bitmap rotateBitmap(String src, Bitmap bitmap) {
		try {
			int orientation = getExifOrientation(src);

			if (orientation == 1) {
				return bitmap;
			}

			Matrix matrix = new Matrix();
			switch (orientation) {
			case 2:
				matrix.setScale(-1, 1);
				break;
			case 3:
				matrix.setRotate(180);
				break;
			case 4:
				matrix.setRotate(180);
				matrix.postScale(-1, 1);
				break;
			case 5:
				matrix.setRotate(90);
				matrix.postScale(-1, 1);
				break;
			case 6:
				matrix.setRotate(90);
				break;
			case 7:
				matrix.setRotate(-90);
				matrix.postScale(-1, 1);
				break;
			case 8:
				matrix.setRotate(-90);
				break;
			default:
				return bitmap;
			}

			try {
				Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0,
						bitmap.getWidth(), bitmap.getHeight(), matrix, true);
				bitmap.recycle();
				return oriented;
			} catch (OutOfMemoryError e) {
				e.printStackTrace();
				return bitmap;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	private static int getExifOrientation(String src) throws IOException {
		int orientation = 1;

		try {
			/**
			 * if your are targeting only api level >= 5 ExifInterface exif =
			 * new ExifInterface(src); orientation =
			 * exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
			 */
			if (Build.VERSION.SDK_INT >= 5) {
				Class<?> exifClass = Class
						.forName("android.media.ExifInterface");
				Constructor<?> exifConstructor = exifClass
						.getConstructor(new Class[] { String.class });
				Object exifInstance = exifConstructor
						.newInstance(new Object[] { src });
				Method getAttributeInt = exifClass.getMethod("getAttributeInt",
						new Class[] { String.class, int.class });
				Field tagOrientationField = exifClass
						.getField("TAG_ORIENTATION");
				String tagOrientation = (String) tagOrientationField.get(null);
				orientation = (Integer) getAttributeInt.invoke(exifInstance,
						new Object[] { tagOrientation, 1 });
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return orientation;
	}

	 public static Bitmap decodeFile(String path) {
		 try {
			 File f = new File(path);

			 BitmapFactory.Options o = new BitmapFactory.Options();
			 o.inJustDecodeBounds = true;
			 BitmapFactory.decodeStream(new FileInputStream(f), null, o);
			 final int REQUIRED_SIZE = 70;
			 int width_tmp = o.outWidth, height_tmp = o.outHeight;
			 int scale = 2;
			 while (true) {
				 if (width_tmp / 2 < REQUIRED_SIZE
						 || height_tmp / 2 < REQUIRED_SIZE)
					 break;
				 width_tmp /= 2;
				 height_tmp /= 2;
				 scale++;
			 }

			 BitmapFactory.Options o2 = new BitmapFactory.Options();
			 o2.inSampleSize = scale;
			 return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		 } catch (FileNotFoundException e) {
		 }
		 return null;
	 }
}