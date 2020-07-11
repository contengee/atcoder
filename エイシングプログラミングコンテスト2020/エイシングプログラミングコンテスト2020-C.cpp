#pragma GCC optimize("Ofast")
#include <bits/stdc++.h>
using namespace std;
using ll = long long;
using ull = unsigned long long;
using pii =  pair<int, int>;
using pll =  pair<long long, long long>;
#define rep(i, n) for(int i = 0; i < (n); ++i)
#define all(x) (x).begin(),(x).end()
constexpr char ln =  '\n';
constexpr long long MOD = 1000000007LL;
//constexpr long long MOD = 998244353LL;
template<class T, class U> inline bool chmax(T &a, U b) { if (a < b) { a = b; return true;} return false; }
template<class T, class U> inline bool chmin(T &a, U b) { if (a > b) { a = b; return true;} return false; }
const ll INF = (ll)1e18 + 1;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr);

    int n; cin >> n;
    vector<int> v;

    for(int x=1; x<100; x++){
        for(int y=1; y<100; y++){
            for(int z=1; z<100; z++){
                ll sum = 0;
                sum = x*x + y*y + z*z + x*y + y*z + z*x;
                if(sum<=10050) v.push_back(sum);
            }
        }
    }
    sort(all(v));

    for(int i=1; i<=n; i++){
        cout << count(all(v), i) << ln;
    }
    return 0;
}
