//=============================================================================
// ファイル: model.h
//=============================================================================
// モデルの設定・描画を制御
//=============================================================================

#ifndef __MODEL_H__
#define __MODEL_H__

// ヘッダファイルのインクルード
#include "animator.h"

// フレーム番号の最大値
int max_frame_count = 450;

// Modelクラスの定義（ModelerViewクラスを継承）
class Model : public ModelerView {
private:
    //〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜
	//第3週課題
    //---------------------------------------------------------------------

	// フレーム番号
    int frame_count;
    
    //-------------------------------------------------------------------------
    // 制御変数
    //-------------------------------------------------------------------------

        // 〜〜〜変数を追加〜〜〜
	double r, posX, posY;
	double fr, fr_sin, fr_sin2, fr_sin3, fr_sin4;

    //〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜


public:
    // コンストラクタ（スーパークラスのコンストラクタを呼び出す）
    Model( int x, int y, int w, int h, char* label ) : ModelerView( x, y, w, h, label )
    {
        //〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜
		//第3週課題
        //---------------------------------------------------------------------

        // フレーム番号の初期化
        frame_count = 0;
        
        //---------------------------------------------------------------------
        // 初期化
        //---------------------------------------------------------------------

            // 〜〜〜変数を初期化〜〜〜
		r = 5.0;
		posX = r;   // r * cos( 0 )
		posY = 0.0;   // r * sin( 0 )

		fr = 0;
		fr_sin = 0;
		fr_sin2 = 0;
		fr_sin3 = 0;
		fr_sin4 = 0;

		//〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜
    }

    //〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜
	//第3週課題
	//---------------------------------------------------------------------

    // 自動アニメーションの設定
    void SetAutomaticAnimation()
    {
        //-----------------------------------------------------------------
        // アニメーション
        //-----------------------------------------------------------------

            // 〜〜〜プログラムを記述〜〜〜
		double th = frame_count*M_PI / 50;
		posX = r * cos(th);
		posY = r * sin(th);

		fr = ((double)frame_count / (double)max_frame_count);
		fr_sin = 1.0f * sin(fr * 2.0f * M_PI);
		fr_sin2 = 1.0f * sin(fr * 4.0f * M_PI);
		fr_sin3 = 1.0f * sin(fr * 6.0f * M_PI);
		fr_sin4 = 1.0f * sin(fr * 8.0f * M_PI);
        //-----------------------------------------------------------------
    }

	// 手動アニメーションの設定
    void SetManualAnimation()
    {
        //-----------------------------------------------------------------
        // アニメーション
        //-----------------------------------------------------------------

            // 〜〜〜プログラムを記述〜〜〜
		double th = frame_count*M_PI / 50;
		posX = r * cos(th);
		posY = r * sin(th);

		fr = ((double)frame_count / (double)max_frame_count);
		fr_sin = 1.0f * sin(fr * 2.0f * M_PI);
		fr_sin2 = 1.0f * sin(fr * 4.0f * M_PI);
		fr_sin3 = 1.0f * sin(fr * 6.0f * M_PI);
		fr_sin4 = 1.0f * sin(fr * 8.0f * M_PI);

		//-----------------------------------------------------------------
    }

    //〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜


    // 描画の前処理
    void BeginPaint()
    {
        // 半透明処理を有効化
        glEnable( GL_BLEND );
        // 混合方法の指定
        glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
    }

    // 描画の後処理
    void EndPaint()
    {
        // 半透明処理を無効化
        glDisable( GL_BLEND );
    }

	//目を描く関数
	//引数は半径、xの回転角度、ｙの回転角度
	void draweye(float r, double xRotated, double yRotated)
	{
		glPushMatrix();
		glRotated(xRotated * 20, 0, 1, 0);
		glRotated(yRotated * 20, 1, 0, 0);
		setDiffuseColor(1.0f, 1.0f, 1.0f, 1.0f);
		drawSphere(r);
		glTranslated(0, 0, -0.8 * r);
		setDiffuseColor(0.3f, 0.3f, 0.3f, 1.0f);
		drawSphere(0.4 * r);
		glPopMatrix();

	}

	//体を描く関数
	//引数はｘの位置、大きさ、ｘｙの角度
	void drawBodyParts(double xPoint, float size, double xRotated, double yRotated)
	{
		glPushMatrix();
		glTranslated(xPoint, 0, 0);
		glScaled(1.1, 1, 1);
		setDiffuseColor(0.7f, 0.7f, 0.7f, 1.0f);
		drawSphere(2 * size);
		glPushMatrix();
		glRotated(90, 1, 0, 0);
		setDiffuseColor(0.5f, 0.5f, 0.5f, 1.0f);
		drawCylinder(0.2 * size, 2.5 * size, 2.5 * size);
		glPopMatrix();
		glScaled((10.0f/11.0f), 1, 1);
		glTranslated(0, 0.7f * size, -1.3f * size);
		glRotated(20, 1, 0, 0);
		draweye(1.0f * size, xRotated, yRotated);
		glPopMatrix();
	}

	//角を描く関数
	void drawTuno()
	{
		glPushMatrix();
		setDiffuseColor(1.0f, 1.0f, 0.3f, 1.0f);
		drawTriangle(1, 0, 0, -1, 0, 0, 0, 3, 0);
		drawTriangle(0, 0, 1, 0, 0, -1, 0, 3, 0);
		glTranslated(0, 3, 0);
		drawSphere(0.3);
		glPopMatrix();
	}

	//ネジを描く関数
	//引数はｘの場所、ネジの高さ
	void drawNeji(double xPoint, double h)
	{
		glPushMatrix();
		glTranslated(xPoint, 0, 0);
		setDiffuseColor(1.0f, 1.0f, 1.0f, 1.0f);
		glRotated(-63, 1, 0, 0);
		drawCylinder(h, 0.2, 0.2);
		glTranslated(0, 0, h);
		glScaled(1, 1, 0.5);
		drawSphere(0.4);
		glTranslated(0, 0, 0.43);
		primitive_nejiana(0, 0, 0.2);
		glPopMatrix();
	}

	//腕を描く関数
	//引数はxの場所、アニメーションのフレーム
	void drawArms(double xPoint, double flame){
		double armSize = 0.7;
		double armThick = 0.2;
		
		glPushMatrix();

		//二の腕部分
		setDiffuseColor(0.7f, 0.7f, 0.7f, 1.0f);
		glTranslated(xPoint, 0, 0);
		glRotated(-47 + GetSliderValue(ARM1) + 30 * fr_sin, 1, 0, 0);
		glRotated(flame * 360 * 2, 0, 0, 1);
		drawCylinder(2, armThick, armThick);
		glTranslated(0, 0, 2);
		drawSphere(armThick);

		//赤側の指につながる方
		glPushMatrix();
		setDiffuseColor(0.5f, 0.5f, 0.5f, 1.0f);
		glRotated(-135 + GetSliderValue(ARM2) + 30 * fr_sin2, 0, 1, 0);//初期45
		drawCylinder(armSize, armThick, armThick);
		setDiffuseColor(1.0f, 0.0f, 0.0f, 1.0f);
		glTranslated(0, 0, armSize);
		glRotated(135 - GetSliderValue(ARM3) - 10 * fr_sin4, 0, 1, 0);
		drawSphere(armThick);
		drawCylinder(armSize, armThick, armThick);
		glPopMatrix();

		//青側の指につながる方
		glPushMatrix();
		setDiffuseColor(0.5f, 0.5f, 0.5f, 1.0f);
		glRotated(135 - GetSliderValue(ARM2) - 30 * fr_sin2, 0, 1, 0);
		drawCylinder(armSize, armThick, armThick);
		setDiffuseColor(0.2f, 0.2f, 1.0f, 1.0f);
		glTranslated(0, 0, armSize);
		glRotated(-135 + GetSliderValue(ARM3) + 10 * fr_sin4, 0, 1, 0);
		drawSphere(armThick);
		drawCylinder(armSize, armThick, armThick);
		glPopMatrix();

		glPopMatrix();
	}

	//雷のプリミティブ
	//引数はｘ、ｙの場所、大きさ
	void primitive_kaminari(double x, double y, double size){
		glPushMatrix();
		setDiffuseColor(1.0f, 1.0f, 0.3f, 1.0f);
		glTranslated(x, y, 0);
		glRotated(fr * 360 * 10, 0, 1, 0);
		glScaled(size/6, size/6, 1);
		glBegin(GL_POLYGON);
		glNormal3d(0, 0, 1);
		glVertex3d(0, 0, 0);
		glVertex3d(-2, -4, 0);
		glVertex3d(2, -4, 0);
		glVertex3d(3, 0, 0);
		glVertex3d(0, 2, 0);
		glVertex3d(2, 4, 0);
		glVertex3d(-2, 2, 0);
		glEnd();
		glPopMatrix();
	}

	//ネジ穴のプリミティブ
	//引数はｘ、ｙの場所、大きさ
	void primitive_nejiana(double x, double y, double size){
		glPushMatrix();
		setDiffuseColor(0.0f, 0.0f, 0.0f, 1.0f);
		glTranslated(x, y, 0);
		glScaled((size / 2), (size / 2), 1);
		glRotated(360 * fr * 3, 0, 0, 1);
		glBegin(GL_POLYGON);
		glNormal3d(0, 0, 1);
		glVertex3d(-3, 1, 0);
		glVertex3d(3, 1, 0);
		glVertex3d(3, -1, 0);
		glVertex3d(-3, -1, 0);
		glEnd();
		glBegin(GL_POLYGON);
		glNormal3d(0, 0, 1);
		glVertex3d(1, -3, 0);
		glVertex3d(1, 3, 0);
		glVertex3d(-1, 3, 0);
		glVertex3d(-1, -3, 0);
		glEnd();
		glPopMatrix();
	}

	//星のプリミティブ
	//引数はｘ、ｙ、ｚの場所、大きさ
	void primitive_star(double x, double y, double z, double size)
	{
		glPushMatrix();
		setDiffuseColor(1.0f, 0.0f, 0.0f, 1.0f);
		glTranslated(x, y, z);
		glRotated(360 * fr * 10, 0, 0, 1);
		glScaled((size / 2), (size / 2), 1);
		glBegin(GL_POLYGON);
		for (int i = 0; i < 10; i++){
			if (i % 2 == 1){
				glVertex3d(2 * sin(M_PI / 5 * i), 2 * cos(M_PI / 5 * i), 0);
			}
			else{
				glVertex3d(sin(M_PI / 5 * i), cos(M_PI / 5 * i), 0);
			}
		}
		glEnd();
		glPopMatrix();
	}


    // オブジェクトの描画
    void draw()
    {
		//〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜
		//第3週課題
		//---------------------------------------------------------------------
        // 自動アニメーションの処理
        if ( IsAutomaticAnimation() && frame_count<max_frame_count ) {
            // フレーム番号の更新
            SetSliderValue( FRAME_CONTROLS, ++frame_count );
            // 自動アニメーション
            SetAutomaticAnimation();
        }
        // 手動アニメーションの処理
        else {
            // フレーム番号を取得
            frame_count = (int)GetSliderValue( FRAME_CONTROLS );
            // 手動アニメーション
            SetManualAnimation();            
        }
		//〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜〜


        // スーパークラスの描画メソッドをコール（必須）
        ModelerView::draw();

		ParticleSystem *ps = ModelerApplication::Instance()->GetParticleSystem();
		Mat4f CameraTransforms = ps->getModelViewMatrix();


        // 描画開始
        BeginPaint();

        //---------------------------------------------------------------------
        // オブジェクトを描画
        //---------------------------------------------------------------------

		 // 〜〜〜プログラムを記述〜〜〜


		//緑色の地面を描画
		glPushMatrix();
		glTranslated(-5, -6, -5);
		setDiffuseColor(0.3f, 1.0f, 0.3f, 1.0f);
		drawBox(10, 0.3, 10);
		glPopMatrix();
		
		//アニメーしょんのジバコイル本体自体の動き
		glRotated(fr * 360 *3, 0, 1, 0);
		glTranslated(0, fr_sin2, 0);
		glRotated(10 * fr_sin, 1, 0, 0);

		//体を描写
		drawBodyParts(0, 1.0f, fr_sin, fr_sin4);
		drawBodyParts(-3, 0.5f, -fr_sin3, fr_sin2);
		drawBodyParts(3, 0.5f, -fr_sin2, -fr_sin);

		//ネジと角を描写
		drawNeji(-3, 1.5 + (0.5 * fr_sin3));
		drawNeji(3,  1.5 + (0.5 * fr_sin3));
		drawTuno();

		//腕を描画する
		drawArms(1.5, -fr);
		drawArms(-1.5, fr);

		//雷を描画
		primitive_kaminari(3, 5, 2);
		primitive_kaminari(-3, 5, 2);

		//流れ星を描画
		glRotated(fr * 360, 0, 1, 0);
		primitive_star(0, -0.9, -5, -0.7);
		glPushMatrix();
		glTranslated(0, -1, -5);
		setDiffuseColor(1.0f, 1.0f, 0.3f, 1.0f);
		ps->SpawnParticles(CameraTransforms);
		glPopMatrix();

		/*
		glTranslated(posX, posY, 0);
		drawSphere(0.5);
		*/
		
        //---------------------------------------------------------------------

        // 描画終了
        EndPaint();
    }
};

// __MODEL_H__
#endif
