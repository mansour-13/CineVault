import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:frontend/service/movie_api_service.dart';

class AddMoviesPage extends StatefulWidget {
  const AddMoviesPage({super.key});

  @override
  State<AddMoviesPage> createState() => _AddMoviesPageState();
}

class _AddMoviesPageState extends State<AddMoviesPage> {
  final TextEditingController searchController = TextEditingController();
  Timer? debounce;
  final MovieApiService movieApiService = MovieApiService();
  List<dynamic> searchResults = [];

  @override
  void dispose() {
    debounce?.cancel();
    searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CineVault')),
      body: Padding(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            TextField(
              controller: searchController,
              onChanged: (value) {
                debounce?.cancel();
                if (value.trim().length < 3) {
                  setState(() {
                    searchResults = [];
                  });
                  return;
                }
                debounce = Timer(const Duration(milliseconds: 400), () async {
                  final response = await movieApiService.searchMovie(value);
                  if (response.statusCode == 200) {
                    final data = jsonDecode(response.body);
                    final movies = data['results'] as List<dynamic>;
                    if (!mounted) return;
                    setState(() {
                      searchResults = movies;
                    });
                  }
                });
              },
              decoration: const InputDecoration(
                labelText: 'Search Movies',
                hintText: 'Enter movie title',
                prefixIcon: Icon(Icons.search),
                border: OutlineInputBorder(),
              ),
            ),
            Expanded(
              child: ListView.builder(
                itemCount: searchResults.length,
                itemBuilder: (context, index) {
                  final movie = searchResults[index];

                  return ListTile(
                    title: Text(movie['title']),
                    subtitle: Text(movie['release_date'] ?? 'Unknown date'),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
