import 'dart:convert';

import 'package:http/http.dart' as http;

class MovieApiService {
  Future<List<dynamic>> getUserMovies() async {
    final response = await http.get(
      Uri.parse('http://localhost:8080/api/user-movie'),
    );
    if (response.statusCode == 200) {
      return jsonDecode(response.body) as List<dynamic>;
    } else {
      throw Exception('Faild to load movies');
    }
  }

  Future<http.Response> searchMovie(String title) async {
    final uri = Uri.http('localhost:8080', '/api/tmdb/search', {
      'title': title,
    });
    final response = await http.get(uri);
    return response;
  }
}
