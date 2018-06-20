//=============================================================================
// ƒtƒ@ƒCƒ‹: model.h
//=============================================================================
// ƒ‚ƒfƒ‹‚Ìİ’èE•`‰æ‚ğ§Œä
//=============================================================================

#ifndef __MODEL_H__
#define __MODEL_H__

// ƒwƒbƒ_ƒtƒ@ƒCƒ‹‚ÌƒCƒ“ƒNƒ‹[ƒh
#include "animator.h"

// ƒtƒŒ[ƒ€”Ô†‚ÌÅ‘å’l
int max_frame_count = 450;

// ModelƒNƒ‰ƒX‚Ì’è‹`iModelerViewƒNƒ‰ƒX‚ğŒp³j
class Model : public ModelerView {
private:
    //```````````````````````````````````
	//‘æ3T‰Û‘è
    //---------------------------------------------------------------------

	// ƒtƒŒ[ƒ€”Ô†
    int frame_count;
    
    //-------------------------------------------------------------------------
    // §Œä•Ï”
    //-------------------------------------------------------------------------

        // ```•Ï”‚ğ’Ç‰Á```
	double r, posX, posY;
	double fr, fr_sin, fr_sin2, fr_sin3, fr_sin4;

    //```````````````````````````````````


public:
    // ƒRƒ“ƒXƒgƒ‰ƒNƒ^iƒX[ƒp[ƒNƒ‰ƒX‚ÌƒRƒ“ƒXƒgƒ‰ƒNƒ^‚ğŒÄ‚Ño‚·j
    Model( int x, int y, int w, int h, char* label ) : ModelerView( x, y, w, h, label )
    {
        //```````````````````````````````````
		//‘æ3T‰Û‘è
        //---------------------------------------------------------------------

        // ƒtƒŒ[ƒ€”Ô†‚Ì‰Šú‰»
        frame_count = 0;
        
        //---------------------------------------------------------------------
        // ‰Šú‰»
        //---------------------------------------------------------------------

            // ```•Ï”‚ğ‰Šú‰»```
		r = 5.0;
		posX = r;   // r * cos( 0 )
		posY = 0.0;   // r * sin( 0 )

		fr = 0;
		fr_sin = 0;
		fr_sin2 = 0;
		fr_sin3 = 0;
		fr_sin4 = 0;

		//```````````````````````````````````
    }

    //```````````````````````````````````
	//‘æ3T‰Û‘è
	//---------------------------------------------------------------------

    // ©“®ƒAƒjƒ[ƒVƒ‡ƒ“‚Ìİ’è
    void SetAutomaticAnimation()
    {
        //-----------------------------------------------------------------
        // ƒAƒjƒ[ƒVƒ‡ƒ“
        //-----------------------------------------------------------------

            // ```ƒvƒƒOƒ‰ƒ€‚ğ‹Lq```
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

	// è“®ƒAƒjƒ[ƒVƒ‡ƒ“‚Ìİ’è
    void SetManualAnimation()
    {
        //-----------------------------------------------------------------
        // ƒAƒjƒ[ƒVƒ‡ƒ“
        //-----------------------------------------------------------------

            // ```ƒvƒƒOƒ‰ƒ€‚ğ‹Lq```
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

    //```````````````````````````````````


    // •`‰æ‚Ì‘Oˆ—
    void BeginPaint()
    {
        // ”¼“§–¾ˆ—‚ğ—LŒø‰»
        glEnable( GL_BLEND );
        // ¬‡•û–@‚Ìw’è
        glBlendFunc( GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA );
    }

    // •`‰æ‚ÌŒãˆ—
    void EndPaint()
    {
        // ”¼“§–¾ˆ—‚ğ–³Œø‰»
        glDisable( GL_BLEND );
    }

	//–Ú‚ğ•`‚­ŠÖ”
	//ˆø”‚Í”¼ŒaAx‚Ì‰ñ“]Šp“xA‚™‚Ì‰ñ“]Šp“x
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

	//‘Ì‚ğ•`‚­ŠÖ”
	//ˆø”‚Í‚˜‚ÌˆÊ’uA‘å‚«‚³A‚˜‚™‚ÌŠp“x
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

	//Šp‚ğ•`‚­ŠÖ”
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

	//ƒlƒW‚ğ•`‚­ŠÖ”
	//ˆø”‚Í‚˜‚ÌêŠAƒlƒW‚Ì‚‚³
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

	//˜r‚ğ•`‚­ŠÖ”
	//ˆø”‚Íx‚ÌêŠAƒAƒjƒ[ƒVƒ‡ƒ“‚ÌƒtƒŒ[ƒ€
	void drawArms(double xPoint, double flame){
		double armSize = 0.7;
		double armThick = 0.2;
		
		glPushMatrix();

		//“ñ‚Ì˜r•”•ª
		setDiffuseColor(0.7f, 0.7f, 0.7f, 1.0f);
		glTranslated(xPoint, 0, 0);
		glRotated(-47 + GetSliderValue(ARM1) + 30 * fr_sin, 1, 0, 0);
		glRotated(flame * 360 * 2, 0, 0, 1);
		drawCylinder(2, armThick, armThick);
		glTranslated(0, 0, 2);
		drawSphere(armThick);

		//Ô‘¤‚Ìw‚É‚Â‚È‚ª‚é•û
		glPushMatrix();
		setDiffuseColor(0.5f, 0.5f, 0.5f, 1.0f);
		glRotated(-135 + GetSliderValue(ARM2) + 30 * fr_sin2, 0, 1, 0);//‰Šú45
		drawCylinder(armSize, armThick, armThick);
		setDiffuseColor(1.0f, 0.0f, 0.0f, 1.0f);
		glTranslated(0, 0, armSize);
		glRotated(135 - GetSliderValue(ARM3) - 10 * fr_sin4, 0, 1, 0);
		drawSphere(armThick);
		drawCylinder(armSize, armThick, armThick);
		glPopMatrix();

		//Â‘¤‚Ìw‚É‚Â‚È‚ª‚é•û
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

	//—‹‚ÌƒvƒŠƒ~ƒeƒBƒu
	//ˆø”‚Í‚˜A‚™‚ÌêŠA‘å‚«‚³
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

	//ƒlƒWŒŠ‚ÌƒvƒŠƒ~ƒeƒBƒu
	//ˆø”‚Í‚˜A‚™‚ÌêŠA‘å‚«‚³
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

	//¯‚ÌƒvƒŠƒ~ƒeƒBƒu
	//ˆø”‚Í‚˜A‚™A‚š‚ÌêŠA‘å‚«‚³
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


    // ƒIƒuƒWƒFƒNƒg‚Ì•`‰æ
    void draw()
    {
		//```````````````````````````````````
		//‘æ3T‰Û‘è
		//---------------------------------------------------------------------
        // ©“®ƒAƒjƒ[ƒVƒ‡ƒ“‚Ìˆ—
        if ( IsAutomaticAnimation() && frame_count<max_frame_count ) {
            // ƒtƒŒ[ƒ€”Ô†‚ÌXV
            SetSliderValue( FRAME_CONTROLS, ++frame_count );
            // ©“®ƒAƒjƒ[ƒVƒ‡ƒ“
            SetAutomaticAnimation();
        }
        // è“®ƒAƒjƒ[ƒVƒ‡ƒ“‚Ìˆ—
        else {
            // ƒtƒŒ[ƒ€”Ô†‚ğæ“¾
            frame_count = (int)GetSliderValue( FRAME_CONTROLS );
            // è“®ƒAƒjƒ[ƒVƒ‡ƒ“
            SetManualAnimation();            
        }
		//```````````````````````````````````


        // ƒX[ƒp[ƒNƒ‰ƒX‚Ì•`‰æƒƒ\ƒbƒh‚ğƒR[ƒ‹i•K{j
        ModelerView::draw();

		ParticleSystem *ps = ModelerApplication::Instance()->GetParticleSystem();
		Mat4f CameraTransforms = ps->getModelViewMatrix();


        // •`‰æŠJn
        BeginPaint();

        //---------------------------------------------------------------------
        // ƒIƒuƒWƒFƒNƒg‚ğ•`‰æ
        //---------------------------------------------------------------------

		 // ```ƒvƒƒOƒ‰ƒ€‚ğ‹Lq```


		//—ÎF‚Ì’n–Ê‚ğ•`‰æ
		glPushMatrix();
		glTranslated(-5, -6, -5);
		setDiffuseColor(0.3f, 1.0f, 0.3f, 1.0f);
		drawBox(10, 0.3, 10);
		glPopMatrix();
		
		//ƒAƒjƒ[‚µ‚å‚ñ‚ÌƒWƒoƒRƒCƒ‹–{‘Ì©‘Ì‚Ì“®‚«
		glRotated(fr * 360 *3, 0, 1, 0);
		glTranslated(0, fr_sin2, 0);
		glRotated(10 * fr_sin, 1, 0, 0);

		//‘Ì‚ğ•`Ê
		drawBodyParts(0, 1.0f, fr_sin, fr_sin4);
		drawBodyParts(-3, 0.5f, -fr_sin3, fr_sin2);
		drawBodyParts(3, 0.5f, -fr_sin2, -fr_sin);

		//ƒlƒW‚ÆŠp‚ğ•`Ê
		drawNeji(-3, 1.5 + (0.5 * fr_sin3));
		drawNeji(3,  1.5 + (0.5 * fr_sin3));
		drawTuno();

		//˜r‚ğ•`‰æ‚·‚é
		drawArms(1.5, -fr);
		drawArms(-1.5, fr);

		//—‹‚ğ•`‰æ
		primitive_kaminari(3, 5, 2);
		primitive_kaminari(-3, 5, 2);

		//—¬‚ê¯‚ğ•`‰æ
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

        // •`‰æI—¹
        EndPaint();
    }
};

// __MODEL_H__
#endif
