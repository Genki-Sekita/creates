//=============================================================================
// �t�@�C��: controller.h
//=============================================================================
// �X���C�_�R���g���[���̐ݒ�
//=============================================================================

#ifndef __CONTROLLER_H__
#define __CONTROLLER_H__

// �w�b�_�t�@�C���̃C���N���[�h
#include "modelerapp.h"

// �t���[���ԍ��̍ő�l
extern int max_frame_count;

// �R���g���[�����ʗp�̗񋓎q
enum CONTROLLERS {

	FRAME_CONTROLS,   // �t���[���R���g���[���i�ύX���Ȃ�����)
    // �`�`�`�񋓎q��ǉ��`�`�`
	ARM1,
	ARM2,
	ARM3,
    NUM_CONTROLS,   // �R���g���[�����i�K�������ɒǉ��j
};

// �R���g���[���̐ݒ�
//�iNUM_CONTROLS������CONTROLLERS�񋓎q�ƂP�F�P�ɑΉ��t����j
void SetController( ModelerControl* controls )
{
	// �t���[���ԍ��̐���p�X���C�_
    controls[FRAME_CONTROLS] = ModelerControl( "Frame Number", 0, (float)max_frame_count, 1, 0 );

    // �`�`�`�X���C�_��ǉ��`�`�`

	controls[ARM1] = ModelerControl("arm1", 0, 360, 1, 180);
	controls[ARM2] = ModelerControl("arm2", 0, 360, 1, 180);
	controls[ARM3] = ModelerControl("arm3", 0, 360, 1, 180);

}

// __CONTROLLER_H__
#endif
