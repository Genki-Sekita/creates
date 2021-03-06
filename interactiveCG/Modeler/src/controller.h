//=============================================================================
// ファイル: controller.h
//=============================================================================
// スライダコントローラの設定
//=============================================================================

#ifndef __CONTROLLER_H__
#define __CONTROLLER_H__

// ヘッダファイルのインクルード
#include "modelerapp.h"

// フレーム番号の最大値
extern int max_frame_count;

// コントローラ識別用の列挙子
enum CONTROLLERS {

	FRAME_CONTROLS,   // フレームコントローラ（変更しないこと)
    // 〜〜〜列挙子を追加〜〜〜
	ARM1,
	ARM2,
	ARM3,
    NUM_CONTROLS,   // コントローラ数（必ず末尾に追加）
};

// コントローラの設定
//（NUM_CONTROLSを除くCONTROLLERS列挙子と１：１に対応付ける）
void SetController( ModelerControl* controls )
{
	// フレーム番号の制御用スライダ
    controls[FRAME_CONTROLS] = ModelerControl( "Frame Number", 0, (float)max_frame_count, 1, 0 );

    // 〜〜〜スライダを追加〜〜〜

	controls[ARM1] = ModelerControl("arm1", 0, 360, 1, 180);
	controls[ARM2] = ModelerControl("arm2", 0, 360, 1, 180);
	controls[ARM3] = ModelerControl("arm3", 0, 360, 1, 180);

}

// __CONTROLLER_H__
#endif
