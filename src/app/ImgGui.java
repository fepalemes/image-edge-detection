package app;

import java.io.File;

import javafx.event.ActionEvent;

import javafx.event.EventHandler;

import javafx.scene.Scene;

import javafx.scene.control.Menu;

import javafx.scene.control.MenuBar;

import javafx.scene.control.MenuItem;

import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javafx.scene.image.PixelReader;

import javafx.scene.image.PixelWriter;

import javafx.scene.image.WritableImage;

import javafx.scene.layout.StackPane;

import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;

import javafx.stage.FileChooser;

import javafx.stage.Stage;

public class ImgGui {

	// Matrizes referentes aos filtros

	double[][] filter_Robert_mx = { { 0, 0, -1.0 }, { 0, 1.0, 0 }, { 0, 0, 0 } };

	double[][] filter_Robert_my = { { -1.0, 0, 0 }, { 0, 1.0, 0 }, { 0, 0, 0 } };

	double[][] filter_Sobel_mx = { { 1.0, 0, -1.0 }, { 2.0, 0, -2.0 }, { 1.0, 0, -1.0 } };

	double[][] filter_Sobel_my = { { -1.0, -2.0, -1.0 }, { 0, 0, 0 }, { 1.0, 2.0, 1.0 } };

	double[][] filter_Prewit_mx = { { 1.0, 0, -1.0 }, { 1.0, 0, -1.0 }, { 1.0, 0, -1.0 } };

	double[][] filter_Prewit_my = { { -1.0, -1.0, -1.0 }, { 0, 0, 0 }, { 1.0, 1.0, 1.0 } };

	double[][] filter_Laplaciano = { { 0, -1.0, 0 }, { -1.0, 4.0, -1.0 }, { 0, -1.0, 0 } };

	public Image image = null;

	ImageView imageView = new ImageView();

	public ImgGui(Stage palco) {

		// Tamanho da janela - altura X largura

		palco.setWidth(500);

		palco.setHeight(500);

		// Menu arquivo

		Menu menuFile = new Menu("Arquivo");

		MenuItem btnReload = new MenuItem("Importar");

		menuFile.getItems().add(btnReload);

		// Menu transformacao

		Menu menuTrans = new Menu("Transformar");

		MenuItem btnGray = new MenuItem("Escala de cinza");

		menuTrans.getItems().add(btnGray);

		// Menu de filtros

		Menu menuTran = new Menu("Filtros");

		MenuItem btnRobertmx = new MenuItem("Robert_mx");

		MenuItem btnRobertmy = new MenuItem("Robert_my");

		MenuItem btnSobelmx = new MenuItem("Sobel_mx");

		MenuItem btnSobelmy = new MenuItem("Sobel_my");

		MenuItem btnPrewitmx = new MenuItem("Prewit_mx");

		MenuItem btnPrewitmy = new MenuItem("Prewit_my");

		MenuItem btnLaplaciano = new MenuItem("Laplaciano");

		menuTran.getItems().add(btnRobertmx);

		menuTran.getItems().add(btnRobertmy);

		menuTran.getItems().add(btnSobelmx);

		menuTran.getItems().add(btnSobelmy);

		menuTran.getItems().add(btnPrewitmx);

		menuTran.getItems().add(btnPrewitmy);

		menuTran.getItems().add(btnLaplaciano);

		// Chamada e ordem do menu

		MenuBar menuBar = new MenuBar();

		menuBar.getMenus().add(menuFile);

		menuBar.getMenus().add(menuTrans);

		menuBar.getMenus().add(menuTran);

		// Acao botao de importacao de imagem

		btnReload.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				try {

					FileChooser fileChooser = new FileChooser();

					File file = fileChooser.showOpenDialog(null);

					if (file != null) {

						String strFile = file.getPath();

						File f = new File(strFile);

						image = new Image(f.toURI().toString());

						imageView.setImage(image);

					}

				} catch (Exception e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

			}

		});

		// Acao botao transformacao de cinza

		btnGray.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				PixelReader pixelReader = image.getPixelReader();

				WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

				PixelWriter pixelWriter = wImage.getPixelWriter();

				for (int x = 0; x < image.getWidth(); x++) {

					for (int y = 0; y < image.getHeight(); y++) {

						int pixel = pixelReader.getArgb(x, y);

						int red = ((pixel >> 16) & 0xff);

						int green = ((pixel >> 8) & 0xff);

						int blue = (pixel & 0xff);

						int grayLevel = (int) (0.2162 * (double) red + 0.7152 * (double) green

								+ 0.0722 * (double) blue) / 3;

						// Ajustar intensidade da escala de cinza em grayLevel

						
						grayLevel = 255 - grayLevel;

						int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;

						// Aplicacao para escala de cinza

						pixelWriter.setArgb(x, y, -gray);

					}

					imageView.setImage(wImage);
					image = wImage;

				}

			}

		});

		// Chamada filtro Roberts

		btnRobertmx.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Robert_mx, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		btnRobertmy.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Robert_my, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		// Chamada filtro Sobel

		btnSobelmx.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Sobel_mx, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		btnSobelmy.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Sobel_my, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		// Chamada filtro Prewit

		btnPrewitmx.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Prewit_mx, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		btnPrewitmy.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Prewit_my, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		// Chamada filtro Laplaciano

		btnLaplaciano.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent e) {

				Image img = filter(filter_Laplaciano, image);

				if (img != null)

					imageView.setImage(img);

			}

		});

		StackPane root = new StackPane();

		root.getChildren().add(imageView);

		Scene scene = new Scene(new VBox(menuBar, root));

		palco.setScene(scene);

		palco.show();

	}

	public Image filter(double[][] filter, Image image) {

		if (image == null)

			return null;

		// Realiza a leitura do pixels

		PixelReader pixelReader = image.getPixelReader();

		// Cria uma imagem gravavel

		WritableImage wImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());

		PixelWriter pixelWriter = wImage.getPixelWriter();

		for (int readY = 0; readY < image.getHeight(); readY++) {

			for (int readX = 0; readX < image.getWidth(); readX++) {

				double r = 0;

				double g = 0;

				double b = 0;

				for (int i = -1; i < 2; i++) {

					for (int j = -1; j < 2; j++) {

						if (readX - i < 0 || readX - i > image.getWidth() - 1 || readY - j < 0

								|| readY - j > image.getHeight() - 1)

							continue;

						r += filter[i + 1][j + 1] * pixelReader.getColor(readX - i, readY - j).getRed();

						g += filter[i + 1][j + 1] * pixelReader.getColor(readX - i, readY - j).getGreen();

						b += filter[i + 1][j + 1] * pixelReader.getColor(readX - i, readY - j).getBlue();

					}

				}

				r = (r < 0) ? 0 : r;

				r = (r > 1) ? 1 : r;

				g = (g < 0) ? 0 : g;

				g = (g > 1) ? 1 : g;

				b = (b < 0) ? 0 : b;

				b = (b > 1) ? 1 : b;

				int ir = (int) (r * 255);

				int ig = (int) (g * 255);

				int ib = (int) (b * 255);

				Color c1 = Color.rgb(ir, ig, ib);

				pixelWriter.setColor(readX, readY, c1);

			}

		}

		return wImage;

	}

}
