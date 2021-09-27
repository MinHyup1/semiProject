		Kakao.init('8406376e4a3016276da81540af46ba74');
        
        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());
function unlinkApp() {
      if (!Kakao.Auth.getAccessToken()) {
        return
      }
      Kakao.Auth.logout(function() {
      })
    }
  Kakao.API.request({
      url: '/v1/user/unlink',
      success: function(res) {
      },
      fail: function(err) {
      },
    })
